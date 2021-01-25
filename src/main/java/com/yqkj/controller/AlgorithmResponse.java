package com.yqkj.controller;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName AlgorithmResquest
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 10:30
 * @Version 1.0
 **/
public class AlgorithmResponse implements Serializable {
    /**
     * 是否运行成功
     */
    private Boolean hasSuccess = Boolean.TRUE;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 输出
     */
    private Object out;

    public  AlgorithmResponse returnFail(String msg){
        this.message=msg;
        this.hasSuccess=Boolean.FALSE;
        return  this;
    }

    public Boolean getHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(Boolean hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getOut() {
        return out;
    }

    public void setOut(Object out) {
        this.out = out;
    }
}
