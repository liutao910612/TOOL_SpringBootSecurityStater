package com.liutao.util.springboot.security.auto;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取RSA相关配置数据
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/7
 * @Modified By:
 */
@ConfigurationProperties(prefix = "spring.encrypt")
public class SecurityProperties {
    private String privateKey;  //RSA加密私钥
    private String charset = "UTF-8";
    private boolean debug = false;  // 开启调试模式，调试模式下不进行加解密操作，用于像Swagger这种在线API测试场景
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
