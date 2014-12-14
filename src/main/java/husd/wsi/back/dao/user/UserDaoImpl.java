package husd.wsi.back.dao.user;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import husd.wsi.back.pojo.User;

@Component
public class UserDaoImpl implements UserDao{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public User find(String username) {
		return (User)sqlSessionTemplate.selectOne("find",username);
	}

	public void save(User user) {
		sqlSessionTemplate.update("save",user);
	}

}
