package nuc.iot.blog.service;

import nuc.iot.blog.dto.BlogDTO;
import nuc.iot.blog.exception.CustomizeErrorCode;
import nuc.iot.blog.exception.CustomizeException;
import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.mapper.UserMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(Blog blog) {
        if (blog.getId() == null) {
            // 创建
            blog.setGmtCreate(System.currentTimeMillis());
            blog.setGmtModified(blog.getGmtCreate());
            blog.setCommentCount(0);
            blogMapper.insert(blog);
        } else {
            // 更新
            Blog dbBlog = blogMapper.selectByPrimaryKey(blog.getId());

            if (dbBlog == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            if (dbBlog.getCreator().longValue() != blog.getCreator().longValue()) {
                throw new CustomizeException(CustomizeErrorCode.INVALID_OPERATION);
            }

            Blog updateBlog = new Blog();
            updateBlog.setId(blog.getId());
            updateBlog.setGmtModified(System.currentTimeMillis());
            updateBlog.setTitle(blog.getTitle());
            updateBlog.setContent(blog.getContent());
            updateBlog.setTag(blog.getTag());

            int updated = blogMapper.updateByExampleSelective(updateBlog);
            System.out.println(updated);
        }
    }

    public BlogDTO getById(Integer id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if (blog == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlog(blog);
        User user = userMapper.findById(blog.getCreator());
        blogDTO.setUser(user);
        return blogDTO;
    }

    public List<Blog> getMyBlogs(Integer id) {
        List<Blog> blogs = blogMapper.selectByUserKey(id);
        return blogs;
    }

    public void deleteBlog(Integer id) {
        int deleted = blogMapper.deleteBlogById(id);
        if (deleted == 0) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
    }

    public List<Blog> list() {
        return blogMapper.selectAllBlog();
    }
}
