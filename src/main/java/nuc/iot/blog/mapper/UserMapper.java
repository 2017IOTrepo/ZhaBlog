package nuc.iot.blog.mapper;

import nuc.iot.blog.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user (name, email, password, gmtCreate, gmtModified, isAdmin, bio) " +
            "values ('${name}', '${email}', '${password}', ${gmtCreate}, ${gmtModified}, ${isAdmin}, '${bio}')")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(User user);

    @Select("select password from user where email = '${email}'")
    String findPwdByEmail(@Param("email") String email);

    @Select("select * from user where id = ${id}")
    User findById(@Param("id") int id);

    @Select("select * from user where email = '${email}'")
    User findUserByEmail(@Param("email") String email);

}
