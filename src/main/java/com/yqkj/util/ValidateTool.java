package com.yqkj.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
/**
 * @ClassName ValidateTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 10:18
 * @Version 1.0
 **/
public class ValidateTool {
    private volatile static Validator validator;

    private static Validator getValidator() {// 使用spring中定义的factory
        if (validator == null) {
            synchronized (ValidateTool.class) {
                if (validator == null) {
                    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                    validator = factory.getValidator();
                }
            }
        }
        return validator;
    }
    public static <T> String validate(T t)  {
        Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(t);
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                String message = constraintViolation.getMessage();
                return message;
            }
        }
        return "";
    }
}
