package spring.io.projects.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Aspect:指定是一个切面
 */
@Aspect
@Component
public class AuthorizationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationAspect.class);

    /**
     * 对于重复的切点,可以使用@Pointcut进行定义, 然后在通知注解内引用.
     * 引用切点@Before("AuthorizationAspect.anyPublicOperation1()") 如果在同一个类下可以省略类名
     * 这只是定义一个切入点，方便各种通知的引用，这个方法是没有实际意义的
     */
    // 指定HomeController下的所有的方法
    @Pointcut("execution(* spring.io.projects.controller.HomeController.*(..))")
    private void anyPublicOperation() {
    }

    /**
     * 需要认证的方法 在有@Auth注解的方法才进行验证 才走个方法 也可以使用execution表达式
     * 
     * @param joinPoint
     */
    // 进入有@Auth注解的方法之前 会先进入这个方法
    @Before("@annotation(spring.io.projects.annotation.Auth)")
    private void apiAuth(JoinPoint joinPoint) {

        LOGGER.info("Hello world for aspect  @Auth");

    }

    // 使用定义的切点
    @Before("AuthorizationAspect.anyPublicOperation()")
    private void apiAuth1(JoinPoint joinPoint) {
        LOGGER.info("Hello world for aspect HomeController");

        // 可以获取连接点的参数
        Object[] strings = joinPoint.getArgs();
        for (Object object : strings) {
            System.out.println("==========:" + object);
        }

    }
}
