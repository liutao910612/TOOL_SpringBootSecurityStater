package com.liutao.util.springboot.security.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liutao.util.springboot.security.annotation.Encrypt;
import com.liutao.util.springboot.security.auto.SecurityProperties;
import com.liutao.util.springboot.security.util.Base64Utils;
import com.liutao.util.springboot.security.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 请求响应处理类
 * 对加了@Encrypt的方法的数据进行加密操作
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/7
 * @Modified By:
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private Logger logger = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private SecurityProperties securityProperties;

	private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

	public static void setEncryptStatus(boolean status) {
		encryptLocal.set(status);
	}

	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
								  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// 可以通过调用EncryptResponseBodyAdvice.setEncryptStatus(false);来动态设置不加密操作
		Boolean status = encryptLocal.get();
		if (status != null && status == false) {
			encryptLocal.remove();
			return body;
		}

		boolean encrypt = false;
		if (returnType.getMethod().isAnnotationPresent(Encrypt.class) && !securityProperties.isDebug()) {
			encrypt = true;
		}
		if (encrypt) {
			String privateKey = securityProperties.getPrivateKey();
			try {
				String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
				if (!StringUtils.hasText(privateKey)) {
					throw new NullPointerException("请配置spring.encrypt.privatekeyc参数");
				}
				byte[] data = content.getBytes();
				byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
				String result = Base64Utils.encode(encodedData);
				return result;
			} catch (Exception e) {
				logger.error("加密数据异常", e);
			}
		}

		return body;
	}

}
