package com.yuewang.rbac.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ExceptionCode
 * @Description custom annotation: extend the error code and response info, for field verification
 * @Author Yue Wang
 * @Date 2023/5/10 11:28
 **/
//@Retention: used to specify the retention policy for the custom annotation.
//`RUNTIME` means that the annotation will be retained at runtime and can be accessed via reflection.
@Retention(RetentionPolicy.RUNTIME)
//@Target: specifies the target element type for which the custom annotation can be used.
//`FIELD` means that the annotation can be applied to fields in a class.
@Target({ElementType.FIELD})
public @interface ExceptionCode {
    //response code
    int value() default 100000;
    //response msg
    String message() default  "Parameter validation error.";
}
