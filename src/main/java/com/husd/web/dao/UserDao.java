package com.husd.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.husd.web.model.User;

@SuppressWarnings({"deprecation", "unused"})
@Repository
public class UserDao extends MysqlBaseDao {

    @SuppressWarnings("unchecked")
    public List<User> queryUserByUsername(String username) {
        return this.write.queryForList("userSqlMap.queryUserByUsername", username);
    }

}
