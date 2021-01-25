package com.yqkj.core.simple;

import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.TwoAddCmdContext;
import com.yqkj.core.cmd.dto.CmdType;

import com.yqkj.core.cmd.dto.out.TwoAddOutput;
import com.yqkj.core.cmd.impl.AbstractCmd;
import com.yqkj.util.CollectionTool;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddSignCmd
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:52
 * @Version 1.0
 **/
@Component
public class TwoAddCmd extends AbstractCmd<TwoAddCmdContext> {

    @Override
    public CmdType cmdName() {
        return CmdType.TWO_SUM;
    }

    /**
     * 通过一个Map 来存储数据， 如果
     * @param twoAddCmdContext
     * @return
     */
    @Override
    public CmdContext cal(TwoAddCmdContext twoAddCmdContext) {
        List<Long> datas = CollectionTool.parseIds(twoAddCmdContext.getRequest().getDatas());
        Map<Long, Integer>  temCache = new HashMap<>(CollectionTool.listSize(datas));
        StringBuffer sb = new StringBuffer();
        for (Integer index =0 ; index < datas.size() ; index++ ){

            if(temCache.isEmpty()){
                temCache.put(datas.get(index) , index);
                continue;
            }
            long su = twoAddCmdContext.getRequest().getIndexData() - datas.get(index);

            if(su >0){
                if(temCache.containsKey(su)){
                    sb.append("[").append(temCache.get(su)).append(",").append(index).append("]").append(",");
                }
            }
            temCache.put(datas.get(index) , index);
        }
        twoAddCmdContext.setResponse(new TwoAddOutput(sb.toString()));
        return twoAddCmdContext;
    }
}
