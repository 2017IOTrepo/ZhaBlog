package nuc.iot.blog.controller;

import nuc.iot.blog.dto.CommentDTO;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.model.User;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/admin/{action}")
    public String admin(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model
                        ) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }


        if ("blogs".equals(action)) {
            model.addAttribute("section", "blogs");
            model.addAttribute("sectionName", "博客管理");
            List<Blog> blogs = blogService.list();
            model.addAttribute("pagination", blogs);
        } else if ("comments".equals(action)) {
            List<CommentDTO> comments = commentService.list();
            model.addAttribute("section", "comments");
            model.addAttribute("pagination", comments);
            model.addAttribute("sectionName", "评论管理");
        }
        return "admin";
    }
}
