package nuc.iot.blog.controller;

import nuc.iot.blog.dto.CommentCreateDTO;
import nuc.iot.blog.dto.ResultDTO;
import nuc.iot.blog.exception.CustomizeErrorCode;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.model.User;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setBlogId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setUserId(user.getId());
        commentService.insert(comment);
        commentService.increaseCommentCount(comment.getBlogId());
        return ResultDTO.okOf();
    }
}
