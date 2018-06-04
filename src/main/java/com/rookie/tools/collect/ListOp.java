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
 * 基于lambda实现
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
        return list
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 对两个list求差集
     *
     * @param list1
     * @param list2
     * @param <T>   返回的差值
     * @return
     */
    public static <T> List<T> diffList(@NonNull List<T> list1, @NonNull List<T> list2) {
        // 首先找出较大的list
        int len1 = list1.size();
        int len2 = list2.size();
        List<T> result;
        // 求出大list与小list的差值
        if (len1 > len2) {
            result = list1
                    .stream()
                    .filter(item -> !list2.contains(item))
                    .collect(Collectors.toList());
        } else {
            result = list2
                    .stream()
                    .filter(item -> !list1.contains(item))
                    .collect(Collectors.toList());
        }
        return result;
    }

    /**
     * 对两个list求交集
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> List<T> intersectList(@NonNull List<T> list1, @NonNull List<T> list2) {
        return list1
                .stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
    }

    /**
     * 返回字符串list中最长的元素
     *
     * @param list
     * @return 最长的元素
     */
    public static String maxInList(@NonNull List<String> list) {
        return list
                .stream()
                .reduce((t1, t2) -> t1.length() >= t2.length() ? t1 : t2)
                .orElse(null);
    }

}