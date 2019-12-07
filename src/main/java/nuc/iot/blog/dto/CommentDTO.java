package nuc.iot.blog.dto;

import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.Comment;
import nuc.iot.blog.model.User;

public class CommentDTO {
    private Integer id;
    private String name;
    private String content;
    private Integer userId;
    private Integer blogId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}
