/**
 * @(#)Test.java, 2018-06-05.
 * <p>
 * Copyright 2018 Stalary.
 */
package com.rookie.tools.annotation;

import lombok.Getter;

/**
 * Test
 *
 * @author lirongqian
 * @since 2018/06/05
 */
public class Test {

    @Sout
    private String name = "stalary";

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println("测试注解" + t.name);

    }
}