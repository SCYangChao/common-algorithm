package com.yqkj.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/11 15:40
 * @Version 1.0
 **/
public class StringTool  {
    /**
     * 是否为空，主要扩展null空字符
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        if(StringUtils.isBlank(str) || "NULL".equalsIgnoreCase(str)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 取空格
     * @param str
     * @return
     */
    public  static  String trim(String str){
        if(isBlank(str)){
            return "";
        }
        return  str.trim();
    }
}
