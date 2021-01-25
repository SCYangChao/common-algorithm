package com.yqkj.core.cmd.impl;


import com.yqkj.core.cmd.AlgorithmCmd;
import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.TwoAddCmdContext;
import com.yqkj.util.ValidateTool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName AbstractCmd
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:43
 * @Version 1.0
 **/
public abstract class AbstractCmd< T extends CmdContext> implements AlgorithmCmd<T> {

    public static final Log log = LogFactory.getLog(AbstractCmd.class);

    @Override
    public CmdContext  excute(T t) {

        String validate = ValidateTool.validate(t.getRequest());

        if(StringUtils.isNotBlank(validate)){
            return  t.returnFail(validate);
        }

        this.before(t);

        this.cal(t);

        this.after(t);

        return t;
    }

    @Override
    public T before(T t) {
        return t;
    }

    @Override
    public T after(T t) {
        return t;
    }

    /**
     * @param t
     * @return
     */
    abstract  public  CmdContext cal(T t);

}
