package nuc.iot.blog.mapper;

import nuc.iot.blog.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user (account_id, name, token, gmt_create, gmt_modified, is_admin, bio) " +
            "values ('${accountId}', '${name}', '${token}', ${gmtCreate}, ${gmtModified}, ${isAdmin}, '${bio}')")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(User user);

    @Select("select * from user where token = '${token}'")
    User findByToken(@Param("token") String token);

    @Select("select id from user where token = '${token}'")
    Integer findIdByToken(@Param("token") String token);

}
