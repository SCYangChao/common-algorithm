package com.yqkj.core.cmd;

import com.yqkj.core.cmd.dto.CmdContext;
import com.yqkj.core.cmd.dto.CmdType;


/**
 * @ClassName AlgorithmCmd
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:49
 * @Version 1.0
 **/
public interface AlgorithmCmd<T extends CmdContext> extends Cmd<T> {
    /**
     * 命令名称
     * @return
     */
     CmdType cmdName();
    /**
     * 前置处理
     * @param t
     * @return
     */
    T before(T t);
    /**
     * 后置处理
     * @param
     * @return
     */
    T after(T t);

}
