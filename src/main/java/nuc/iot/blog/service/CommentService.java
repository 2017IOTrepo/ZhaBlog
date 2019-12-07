package nuc.iot.blog.service;

import nuc.iot.blog.exception.CustomizeErrorCode;
import nuc.iot.blog.exception.CustomizeException;
import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.mapper.CommentMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    public List<Comment> getCommentListBytId(Integer id) {
        List<Comment> comments = commentMapper.selectByBlogId(id);
        return comments;
    }

    public void insert(Comment comment) {
        // 回复问题
        Blog blog = blogMapper.selectByPrimaryKey(comment.getBlogId());
        if (blog == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        commentMapper.insert(comment);
    }
}