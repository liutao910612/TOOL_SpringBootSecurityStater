package com.liutao.util.springboot.security.annotation;

import java.lang.annotation.*;

/**
 * 解密注解
 * 
 * 加了此注解的接口将进行数据解密操作
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/7
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

}
