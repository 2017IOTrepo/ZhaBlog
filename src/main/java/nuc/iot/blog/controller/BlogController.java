package nuc.iot.blog.controller;

import nuc.iot.blog.dto.BlogDTO;
import nuc.iot.blog.dto.CommentDTO;
import nuc.iot.blog.exception.CustomizeErrorCode;
import nuc.iot.blog.exception.CustomizeException;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable(name = "id") String id, Model model) {
        Integer blogId = null;
        try {
            blogId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }

        BlogDTO blogDTO = blogService.getById(blogId);
        List<CommentDTO> comments = commentService.getCommentListBytId(blogId);

//        TODO:累加阅读数
//        blogService.incView(blogId);
        model.addAttribute("blog", blogDTO);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
