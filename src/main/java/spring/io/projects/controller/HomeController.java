package spring.io.projects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 * 
 * @author Mr.Zhang
 * @Date 2016年11月27日
 * @Email zhangjun150429@qq.com
 */
@Controller
public class HomeController {

    @GetMapping(value = { "/", "/index", "/index.html" })
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value = "index1")
    public String demo() {

        return "index1";
    }

    @GetMapping(value = "personal")
    public String personal() {

        return "personal";
    }

}
