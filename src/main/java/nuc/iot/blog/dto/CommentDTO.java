package nuc.iot.blog.dto;

import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.model.User;

public class CommentDTO {
    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
