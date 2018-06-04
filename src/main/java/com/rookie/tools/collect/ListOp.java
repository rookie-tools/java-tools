/**
 * @(#)ListOp.java, 2018-06-02.
 * <p>
 * Copyright 2018 Stalary.
 */
package com.rookie.tools.collect;

import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ListOp
 * 对list操作的工具类
 *
 * @author lirongqian
 * @since 2018/06/02
 */
public class ListOp {

    /**
     * 对list进行去重
     *
     * @param list
     * @param <T>
     * @return 去重后的list
     */
    public static <T> List<T> distinctList(@NonNull List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

}