package nuc.iot.blog.controller;

import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.mapper.UserMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/")
    public String index(
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.isAdmin()) {
            return "admin";
        }


        List<Blog> blogs = blogMapper.selectAllBlog();
        model.addAttribute("blogs", blogs);

        return "index";
    }

}
