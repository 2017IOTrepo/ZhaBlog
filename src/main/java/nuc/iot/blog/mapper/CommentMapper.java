package nuc.iot.blog.mapper;

import nuc.iot.blog.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Select("select * from comment where id = ${id}")
    List<Comment> selectByBlogId(@Param("id") Integer id);

}
