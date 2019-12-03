package nuc.iot.blog.mapper;

import nuc.iot.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user (account_id, name, token, gmt_create, gmt_modified, is_admin) " +
            "values ('${accountId}', '${name}', '${token}', ${gmtCreate}, ${gmtModified}, ${isAdmin})")
    void insert(User user);

}
