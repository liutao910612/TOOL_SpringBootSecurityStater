package com.liutao.util.springboot.security.selector;

import com.liutao.util.springboot.security.annotation.EnableSecurity;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author: LIUTAO
 * @Date: Created in 2018/8/25  11:08
 * @Modified By:
 */
public class SecurityImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), null);
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
        return StringUtils.toStringArray(configurations);
    }

    protected Class<?> getSpringFactoriesLoaderFactoryClass() {
        return EnableSecurity.class;
    }
}