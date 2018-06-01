/**
 * @(#)MapOp.java, 2018-06-01.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rookie.tools.collect;

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
     * 对值进行排序
     * @param map
     * @param reverse
     * @param <K>
     * @param <V>
     * @return
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

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("stalary", 1);
        map.put("hawk", 3);
        map.put("claire", 2);
        System.out.println(map);
        map = sortByValue(map, true);
        System.out.println(map);
    }
}