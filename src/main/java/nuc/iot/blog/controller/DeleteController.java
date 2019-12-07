package nuc.iot.blog.controller;

import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/delete/blog/{id}")
    public String doBlogDelete(
            @PathVariable(name = "id") Integer id,
            HttpServletRequest request,
            Model model) {

        blogService.deleteBlog(id);

        return "redirect:/profile";
    }

    @GetMapping("/delete/comment/{id}/{blogid}")
    public String doCommentDelete(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "blogid") Integer blogid,
            HttpServletRequest request,
            Model model) {

        commentService.deleteById(id);

        return "redirect:/blog/" + String.valueOf(blogid);
    }
}
