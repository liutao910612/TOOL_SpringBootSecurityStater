package com.liutao.util.springboot.security.annotation;

import com.liutao.util.springboot.security.selector.SecurityImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用安全Starter
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/7
 * @Modified By:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SecurityImportSelector.class})
public @interface EnableSecurity {

}
