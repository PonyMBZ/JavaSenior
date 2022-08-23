package com.zzuli.java;

/**
 * @auther pony
 * @create 2022-03-06 18:18
 */
public @interface MyAnnotation {
    String value() default "hello";
}
