package com.example.songwei.androidpractice.todayinformation.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: SongWei
 * Date:   2019/10/4
 * Usage:
 */
@Retention(RetentionPolicy.RUNTIME) //运行时 注解
@Target(ElementType.TYPE)//类 接口 注解
public @interface ViewInject {
    int mainlayoutid() default -1;
}
