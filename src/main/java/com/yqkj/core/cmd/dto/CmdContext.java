package com.yqkj.core.cmd.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName CmdContext
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:32
 * @Version 1.0
 **/
public class CmdContext<T,R>  implements Serializable {
    /**
     *  命令执行是否成功
     */
    private boolean executeResult = Boolean.TRUE;
    /**
     * 命令类型
     */
    private CmdType cmdType;

    private String msg = "成功";
    /**
     * 入参
     */
    private T request;
    /**
     * 返回
     */
    private R response;

    private Map<String , String> param;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public  CmdContext returnFail(String msg){
        this.executeResult= Boolean.FALSE;
        this.msg= msg;
        return  this;
    }
    public boolean isExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(boolean executeResult) {
        this.executeResult = executeResult;
    }

    public CmdType getCmdType() {
        return cmdType;
    }

    public void setCmdType(CmdType cmdType) {
        this.cmdType = cmdType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
    }
}
