package spring.io.projects.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册
 * 
 * @author Mr.Zhang
 * @Date 2016年11月27日
 * @Email zhangjun150429@qq.com
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 注册页面
     * 
     * @param model
     * @return
     */
    @GetMapping(value = { "", "/", "/index" })
    public String register() {
        return "register";
    }

}
