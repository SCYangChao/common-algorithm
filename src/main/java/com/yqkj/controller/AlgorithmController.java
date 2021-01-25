package com.yqkj.controller;

import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.CmdType;
import com.yqkj.core.cmd.impl.CmdProcessorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


/**
 * @ClassName AlgorithmController
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 10:23
 * @Version 1.0
 **/
@RestController
public class AlgorithmController {

    @Autowired
    private CmdProcessorService cmdProcessorService;

    @RequestMapping("/algorithm")
    public  AlgorithmResponse algorithm(@RequestBody AlgorithmResquest algorithmResquest){
        AlgorithmResponse response = new AlgorithmResponse();
        CmdContext<Object, Object> objectObjectCmdContext = new CmdContext<>();
        objectObjectCmdContext.setParam(algorithmResquest.getInput());
        if (StringUtils.isBlank(algorithmResquest.getAlgorithmName())){
            return  response.returnFail("算法名称参数不能为空!");
        }
        CmdType cmdType = CmdType.valueOf(algorithmResquest.getAlgorithmName());
        if(Objects.isNull(cmdType)){
            return  response.returnFail("算法非法!");
        }
        objectObjectCmdContext.setCmdType(cmdType);
        CmdContext excute = cmdProcessorService.excute(objectObjectCmdContext);
        response.setHasSuccess(excute.isExecuteResult());
        response.setMessage(excute.getMsg());
        response.setOut(excute.getResponse());

        return response;

    }

}
