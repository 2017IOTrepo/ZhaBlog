package nuc.iot.blog.controller;

import nuc.iot.blog.dto.BlogDTO;
import nuc.iot.blog.exception.CustomizeErrorCode;
import nuc.iot.blog.exception.CustomizeException;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/blog/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Integer blogId = null;
        try {
            blogId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }

        BlogDTO blogDTO = blogService.getById(blogId);
//        List<CommentDTO> comments = commentService.getCommentListBytId(questionId);
//
//
//        List<QuestionDTO> relatedQuestions = questionService.select(questionDTO);
//        List<CommentDTO> comments = commentService.listByTargetId(RelatedblogId, CommentTypeEnum.QUESTION);
//        累加阅读数
//        questionService.incView(blogId);
//        model.addAttribute("question", questionDTO);
//        model.addAttribute("comments", comments);
//        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
