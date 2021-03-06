package nuc.iot.blog.mapper;

import nuc.iot.blog.model.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogMapper {

    @Insert("insert into blog (title, content, gmtCreate, gmtModified, creator, tag) " +
            "values ('${title}', '${content}', ${gmtCreate}, ${gmtModified}, ${creator}, '${tag}')")
    void insert(Blog blog);

    @Delete("delete from blog where id = ${id}")
    int deleteBlogById(@Param("id") int id);

    @Select("select * from blog where id = ${id}")
    Blog selectByPrimaryKey(@Param("id") int id);

    @Select("select * from blog where creator = ${id}")
    List<Blog> selectByUserKey(@Param("id") int id);

    @Update("update blog set gmtModified=${gmtModified}, " +
            "title='${title}', content='${content}', tag='${tag}' where id = ${id}")
    int updateByExampleSelective(Blog updateBlog);

    @Update("update blog set commentCount = commentCount+1 where id = ${id}")
    int increaseCommentCount(@Param("id") int blogid);

    @Update("update blog set commentCount = commentCount-1 where id = ${id}")
    int decreaseCommentCount(@Param("id") int blogid);

    @Select("select * from blog")
    List<Blog> selectAllBlog();
}
