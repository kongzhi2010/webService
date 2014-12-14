package husd.wsi.back.service;

import javax.annotation.Resource;

import husd.wsi.back.dao.user.UserDao;
import husd.wsi.back.pojo.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public void saveUser(User user){
		if(userDao.find(user.getUsername())!=null){
			//�����û��Ѿ�ע����
			return;
		}
		userDao.save(user);
	}

	public User findUser(String username) {
		return userDao.find(username);
	}
}
