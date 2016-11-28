package spring.io.projects.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String loginViw() {

        if (SecurityUtils.getSubject().isAuthenticated()) {
            LOGGER.debug("已经登录了");
            return "redirect:/index";
        } else {
            LOGGER.debug("未登录");
            return "login";
        }
    }

    /**
     * 登录失败
     * 
     * @param request
     * @param model
     * @return
     */
    @PostMapping(value = "login")
    public String loginFail(HttpServletRequest request, Model model) {

        LOGGER.info("Login Failed!");

        Object shiroLoginFailure = request.getAttribute("shiroLoginFailure");

        String shiroLoginFailureMsg = "登录失败:";
        if (shiroLoginFailure.equals(UnknownAccountException.class.getName())) {
            LOGGER.error("未知帐号");
            shiroLoginFailureMsg += "该账号不存在";
        } else if (shiroLoginFailure.equals(IncorrectCredentialsException.class.getName())) {
            LOGGER.error("密码错误");
            shiroLoginFailureMsg += "密码错误";
        } else if (shiroLoginFailure.equals(DisabledAccountException.class.getName())) {
            LOGGER.error("帐号禁用");
            shiroLoginFailureMsg += "帐号禁用";
        } else if (shiroLoginFailure.equals(ExpiredCredentialsException.class.getName())) {
            LOGGER.error("密码过期");
            shiroLoginFailureMsg += "密码过期";
        } else if (shiroLoginFailure.equals(LockedAccountException.class.getName())) {
            LOGGER.error("帐号锁定");
            shiroLoginFailureMsg += "账号锁定";
        } else {
            LOGGER.error("认证失败:" + shiroLoginFailure);
            shiroLoginFailureMsg += shiroLoginFailure.toString();
        }
        // 用户名回显
        Object login_fail_review_username = SecurityUtils.getSubject().getSession()
                .getAttribute("login_fail_review_username");

        model.addAttribute("login_fail_review_username", login_fail_review_username);
        // 错误信息
        model.addAttribute("login_error_msg", shiroLoginFailureMsg);
        // 登录失败后,添加状态为0
        model.addAttribute("login_status", "0");
        return "login";
    }

    /**
     * 登录成功
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login/success")
    public String loginSucc(HttpServletRequest request, Model model) {
        LOGGER.info("登录成功");
        return "home";
    }

}
