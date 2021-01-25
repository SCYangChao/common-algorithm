package com.yqkj.core.cmd.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yqkj.core.cmd.AlgorithmCmd;
import com.yqkj.core.cmd.Cmd;
import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.CmdType;
import com.yqkj.util.CollectionTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TaskCmdService
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:56
 * @Version 1.0
 **/
@Service
public class CmdProcessorService implements Cmd<CmdContext>, ApplicationContextAware , InitializingBean {

    public static final Log log = LogFactory.getLog(CmdProcessorService.class);

    private ApplicationContext applicationContext;
    /**
     * 命令缓存
     */
    private static Map<CmdType, AlgorithmCmd> map = new ConcurrentHashMap<>();

    @Override
    public CmdContext excute(CmdContext cmdContext) {

        Thread.currentThread().setName("cmdProcessorService_"+ System.currentTimeMillis());
        log.info(String.format("excute info:%s start" , cmdContext.toString()));

        if(Objects.isNull(cmdContext.getCmdType())) {
            return cmdContext.returnFail("命令不为空!");
        }
        if(!map.containsKey(cmdContext.getCmdType())){
            return cmdContext.returnFail("命令非法!");
        }

        AlgorithmCmd algorithmCmd = map.get(cmdContext.getCmdType());
        long start  = System.currentTimeMillis();
        Class<?>[] cals = BeanUtils.findMethodWithMinimalParameters(algorithmCmd.getClass(), "cal").getParameterTypes();
        Class<?> cmdContextClass = null;
        if(!CollectionTool.isNull(cals)){
            cmdContextClass = cals[0];
        }
        Type actualTypeArgument = ((ParameterizedType) cmdContextClass.getGenericSuperclass()).getActualTypeArguments()[0];

        if(Objects.isNull(actualTypeArgument)){
            return cmdContext.returnFail("参数非法!");
        }

        CmdContext context = initCmdContext(cmdContext, cmdContextClass, actualTypeArgument);

        log.info(String.format("执行命令引擎为：%s , 开始时间：%s" , algorithmCmd.getClass() , start));

        CmdContext excute = null;
        try {

            excute = (CmdContext) algorithmCmd.excute(context);

        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
           return cmdContext.returnFail("业务处理异常!");
        }

        if(!Objects.isNull(excute) && !excute.isExecuteResult()){
            log.info(String.format("执行命令引擎为：%s ,失败" , algorithmCmd.getClass()));
            return  cmdContext.returnFail(excute.getMsg());
        }

        log.info(String.format("执行命令引擎为：%s , 结束时间：%s" , algorithmCmd.getClass() , (System.currentTimeMillis()-start)));

        log.info(String.format("excute info:%s end" , cmdContext.toString()));
        return excute;
    }

    private CmdContext initCmdContext(CmdContext cmdContext, Class<?> cmdContextClass, Type actualTypeArgument) {
        try {
            CmdContext o = (CmdContext) cmdContextClass.newInstance();
            BeanUtils.copyProperties(cmdContext, o);
            String jsonString = JSONObject.toJSONString(cmdContext.getParam());
            Object object = JSONObject.parseObject(jsonString, actualTypeArgument);
            o.setRequest(object);
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化命令!");
        Map<String, AlgorithmCmd> algorithmCmdMap = applicationContext.getBeansOfType(AlgorithmCmd.class);

        if(!CollectionTool.isNull(algorithmCmdMap)) {
            algorithmCmdMap.forEach((k,v)->{
                CmdType cmdType = v.cmdName();
                if(!Objects.isNull(cmdType)) {
                    map.put(cmdType, v);
                }
            });
        }
        log.info("初始化命令结束 !");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
