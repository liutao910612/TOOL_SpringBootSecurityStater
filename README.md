# spring-boot-starter-security
## 1 概述
针对SpringBoot项目中的参数传递使用RSA加密机制保证安全性进行封装成工具，实现自动加密返回数据、解密传入数据并映射成json
## 2 使用 <br/>
这里的使用就比较简单了。<br/>
(1)将工具导入项目中。<br/>
(2)在配置文件application.properties中添加配置内容。<br/>

spring.encrypt.privateKey=MIICdgIB....<br/>
spring.encrypt.debug=false<br/>
上面的主要功能就是添加RSA加密和解密的私钥，并且将debug开关置为false（如果置为true将不会进行加密和解密操作）。<br/>

(3)在启动类上添加EnableSecurity注解<br/>
```
@SpringBootApplication
@ComponentScan(basePackages={"com.liutao.swagger"})
@EnableSecurity
public class Application {
 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
(4)在需要加密的方法上添加Encrypt注解（解密为Decrypt）<br/>
```
@Encrypt
    @ApiOperation(value="获取用户")
    @GetMapping("user")
    public User getUser(){
        User user = new User();
        user.setName("liutao");
        user.setId("1212");
        user.setPassword("123456");
        return user;
    }
```
运行代码，我们就可以看见自动解密和加密工具生效。
