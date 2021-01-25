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
public class AlgorithmResquest implements Serializable {
    /**
     * 算法
     */
    private String algorithmName;
    /**
     * 参数
     */
    private Map<String , String> input;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public void setInput(Map<String, String> input) {
        this.input = input;
    }
}
