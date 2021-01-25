package com.yqkj.core.cmd.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TwoAddOutput
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 9:42
 * @Version 1.0
 **/
@Data
public class TwoAddInput implements Serializable {
    /**
     * 数据数组
     */
    private String datas;
    /**
     * 查找的数据
     */
    private Long  indexData;
}
