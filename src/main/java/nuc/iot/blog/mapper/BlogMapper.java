package nuc.iot.blog.mapper;

import nuc.iot.blog.model.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BlogMapper {

    @Insert("insert into blog (title, content, gmt_create, gmt_modified, creator, tag) " +
            "values ('${title}', '${content}', ${gmtCreate}, ${gmtModified}, ${creator}, '${tag}')")
    void insert(Blog blog);

    @Delete("")
    void delete();

    @Update("")
    void update();

    @Select("select * from blog where id = id")
    Blog selectByPrimaryKey(@Param("id") Integer id);

    int updateByExampleSelective(Blog updateBlog);
}
