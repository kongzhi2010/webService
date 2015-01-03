package husd.wsi.dao;

import husd.wsi.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {

	User verifyUserExistOrNot(String username);

	User findByUsernameAndPassword(HashMap<String,String> map);

	void save(HashMap<String,String> map);

	List findAllUsers();

}