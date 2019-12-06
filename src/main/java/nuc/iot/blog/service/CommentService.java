package nuc.iot.blog.service;

import nuc.iot.blog.mapper.CommentMapper;
import nuc.iot.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentListBytId(Integer id) {
        List<Comment> comments = commentMapper.selectByBlogId(id);
        return comments;
    }
}
