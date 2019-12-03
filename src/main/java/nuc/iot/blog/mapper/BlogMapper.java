package nuc.iot.blog.mapper;

import nuc.iot.blog.model.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BlogMapper {

    @Insert("insert into blog (title, content, gmt_create, gmt_modified, creator, tag) " +
            "values ('${title}', '${content}', ${gmtCreate}, ${gmtModified}, ${creator}, '${tag}')")
    void create(Blog blog);
}
