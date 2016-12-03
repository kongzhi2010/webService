package husd.web.dao;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import husd.web.model.User;

@SuppressWarnings({"deprecation", "unused"})
@Repository
public class UserDao extends MysqlBaseDao {

    @SuppressWarnings("unchecked")
    public List<User> queryUserByUsername(String username) {
        return this.write.queryForList("userSqlMap.queryUserByUsername", username);
    }

}
