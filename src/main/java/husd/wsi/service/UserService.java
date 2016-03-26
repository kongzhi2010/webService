package husd.wsi.service;

import husd.wsi.model.User;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUser(String username) {
        return new User();
    }

    public User verifyUser(String username, String password) {
        return new User();
    }

    public List findAllUsers() {
        return Collections.EMPTY_LIST;
    }
}
