package nuc.iot.blog.controller;

import nuc.iot.blog.mapper.UserMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import nuc.iot.blog.util.BCryptUtils;
import nuc.iot.blog.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {

        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        model.addAttribute("email", email);
        model.addAttribute("password", password);

        if (email == null || email.equals("")) {
            model.addAttribute("state", 1);
            return "login";
        } else if (password == null || password.equals("")) {
            model.addAttribute("state", 2);
            return "login";
        } else {
            model.addAttribute("state", -1);
        }

        String pwd = userMapper.findPwdByEmail(email);

        if (BCryptUtils.match(password, pwd)) {
            User user = userMapper.findUserByEmail(email);
            response.addCookie(new Cookie("user_id", String.valueOf(user.getId())));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("state", 2);
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(HttpServletRequest request) {

        return "register";
    }

    /**
     * 这里懒得验证邮箱是否被注册的逻辑了
     */
    @PostMapping("/register")
    public String register(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("password", password);

        if (name == null || name.equals("")) {
            model.addAttribute("state", 0);
            return "register";
        } else if (email == null || email.equals("")) {
            model.addAttribute("state", 1);
            return "register";
        } else if (password == null || password.equals("")) {
            model.addAttribute("state", 2);
            return "register";
        } else {
            model.addAttribute("state", -1);
        }

        if (userMapper.findPwdByEmail(email) != null) {
            model.addAttribute("state", 1);
            return "register";
        }

        Long curr = System.currentTimeMillis();

        User user = new User(
                BCryptUtils.encode(password),
                name,
                email,
                curr,
                curr,
                false,
                "这个人没写呢"
        );

        userMapper.insert(user);

        response.addCookie(new Cookie("user_id", String.valueOf(user.getId())));
        request.getSession().setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("user_id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
