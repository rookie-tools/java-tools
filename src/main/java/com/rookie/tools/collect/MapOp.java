/**
 * @(#)MapOp.java, 2018-06-01.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rookie.tools.collect;

import lombok.NonNull;

import java.util.*;

/**
 * MapOp
 * 对Map操作的工具类
 *
 * @author lirongqian
 * @since 2018/06/01
 */
public class MapOp {

    /**
     * 按值进行排序
     * @param map
     * @param reverse 是否反转
     * @param <K>
     * @param <V>
     * @return 排序后的map
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> sortByValue(Map<K, V> map, final boolean reverse) {
        List list = new LinkedList(map.entrySet());
        list.sort((o1, o2) -> {
            if (reverse) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
            return ((Comparable) ((Map.Entry) (o1)).getValue())
                    .compareTo(((Map.Entry) (o2)).getValue());
        });
        Map result = new LinkedHashMap();
        for (Object aList : list) {
            Map.Entry entry = (Map.Entry) aList;
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 判断两个Map是否相等，注意第一个map不能为null
     * @return true/false
     */
    public static <K, V> boolean mapEqual(@NonNull Map<K, V> map1, Map<K, V> map2) {
        if (map2 == null) {
            return false;
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : map1.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                return false;
            }
            if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

}