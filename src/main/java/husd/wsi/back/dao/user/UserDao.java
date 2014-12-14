package husd.wsi.back.dao.user;

import husd.wsi.back.pojo.User;

public interface UserDao {

	User find(String username);

	void save(User user);
}
