package nuc.iot.blog.controller;

import nuc.iot.blog.mapper.UserMapper;
import nuc.iot.blog.model.User;
import nuc.iot.blog.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String token = CookieUtils.getValue(cookies, "token");

        if (token != null) {
            User user = userMapper.findByToken(token);
            request.getSession().setAttribute("user", user);
        } else {
            request.getSession().setAttribute("user", null);
        }

        return "index";
    }

}
