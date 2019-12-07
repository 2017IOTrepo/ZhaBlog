package nuc.iot.blog.controller;

import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import nuc.iot.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/profile")
    public String profile(HttpServletRequest request,
                          Model model
    ) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        List<Blog> myBlogs = blogService.getMyBlogs(user.getId());

        model.addAttribute("blogs", myBlogs);

        return "profile";
    }
}
