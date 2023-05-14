package com.yuewang.rbac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Auth
 * @Description is used to mark whether this API needs to be managed by permission control:
 * A Controller class is considered to be a set of interfaces' module and the final interface authority id is the module id + method id
 * @Author Yue Wang
 * @Date 2023/5/14 21:10
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE}) // Indicates that this annotation can be applied to both classes and methods.
public @interface Auth {

    /**
     * permission ID，unique
     */
    long id();
    /**
     * permission name
     */
    String name();

}
