package com.yqkj.core.simple;

import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.TwoAddCmdContext;
import com.yqkj.core.cmd.dto.CmdType;

import com.yqkj.core.cmd.impl.AbstractCmd;
import org.springframework.stereotype.Component;
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

    @Override
    public CmdContext cal(TwoAddCmdContext twoAddCmdContext) {


        return null;
    }
}
