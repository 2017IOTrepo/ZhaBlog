package nuc.iot.blog.dto;

import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;

public class BlogDTO {
    private Blog blog;
    private User user;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
