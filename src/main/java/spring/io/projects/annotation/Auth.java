package spring.io.projects.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果一个项目只是给app或第三方提供接口的话,并不需要集成shiro,但是我们对一些特使方法的访问请求进行验证, 这时我们就可以使用切面的方式来完成
 * 自定义注解 @Auth 自动检测 方法上使用这个注解意味着需要进行身份验证
 * 
 * @author Mr.Zhang
 * @Date 2016年10月9日
 * @Email zhangjun150429@qq.com
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
}
