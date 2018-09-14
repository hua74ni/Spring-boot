package com.landicorp.marketing.annotation;

import java.lang.annotation.*;

/**
 * 自定义mongodb字段名注解
 * Created by jiangjt on 2017/9/20.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MongoField {
    String field() default "";
}
