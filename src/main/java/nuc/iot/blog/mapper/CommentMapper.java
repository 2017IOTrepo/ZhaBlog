package nuc.iot.blog.mapper;

import nuc.iot.blog.dto.CommentDTO;
import nuc.iot.blog.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Select("select * from comment where blogId = ${id}")
    List<Comment> selectByBlogId(@Param("id") Integer id);

    @Select("select comment.id, name, content, userId, blogId from user, comment " +
            "where user.id=comment.userId")
    List<CommentDTO> selectAllComments();

    @Select("select comment.id, name, content, userId, blogId from user, comment " +
            "where user.id=comment.userId and blogId=${blogId}")
    List<CommentDTO> selectDTOByBlogId(@Param("blogId") Integer blogId);


    @Insert("insert into comment (content, blogId, userId) " +
            "values ('${content}', ${blogId}, ${userId})")
    int insert(Comment comment);

    @Delete("delete from comment where id = ${id}")
    int deleteBlogById(@Param("id") int id);

}
