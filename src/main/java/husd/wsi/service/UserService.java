package husd.wsi.service;

import husd.wsi.dao.UserMapper;
import husd.wsi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    public void saveUser(String username, String password, String email) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        map.put("email", email);
        userMapper.save(map);
    }

    public User findUser(String username) {
        return userMapper.verifyUserExistOrNot(username);
    }

    public User verifyUser(String username, String password) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        return userMapper.findByUsernameAndPassword(map);
    }

    public List findAllUsers() {
        return userMapper.findAllUsers();
    }
}
