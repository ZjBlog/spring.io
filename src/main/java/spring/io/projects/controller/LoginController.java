package spring.io.projects.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录
 * 
 * @author Mr.Zhang
 * @Date 2016年11月22日
 * @Email zhangjun150429@qq.com
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录页面(未登录)/首页(已登录)
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/login")
    public String loginViw(Model model) {
        return "login";
        // if (SecurityUtils.getSubject().isAuthenticated()) {
        // LOGGER.debug("登录成功 进入主页");
        // return "redirect:/";
        // } else {
        // LOGGER.debug("没有登录进入login页面");
        // return "login";
        // }
    }

}
