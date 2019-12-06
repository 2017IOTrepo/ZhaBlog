package nuc.iot.blog.service;

import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogService {
    @Autowired
    BlogMapper blogMapper;

    public void createOrUpdate(Blog blog) {
        if (blog.getId() == null) {
            // 创建
            blog.setGmtCreate(System.currentTimeMillis());
            blog.setGmtModified(blog.getGmtCreate());
            blog.setViewCount(0);
            blog.setLikeCount(0);
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
            updateBlog.setGmtModified(System.currentTimeMillis());
            updateBlog.setTitle(blog.getTitle());
            updateBlog.setContent(blog.getContent());
            updateBlog.setTag(blog.getTag());

            int updated = blogMapper.updateByExampleSelective(updateBlog);
        }
    }
}
