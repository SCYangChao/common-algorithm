package com.yqkj.core.cmd;

import com.yqkj.core.cmd.dto.CmdContext;
/**
 * @ClassName Cmd
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:48
 * @Version 1.0
 **/
public interface Cmd<T extends CmdContext>{
    /**
     * 命令执行
     * @return
     */
    CmdContext excute(T t);

}
