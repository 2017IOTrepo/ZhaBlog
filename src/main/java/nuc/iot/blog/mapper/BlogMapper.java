package nuc.iot.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {

    @Insert("insert into blog (title, content, gmt_create, gmt_modified, creator, tag) " +
            "values ('${title}', '${content}', ${gmtCreate}, ${gmtModified}, ${creator}, '${tag}')")
    void create();
}
