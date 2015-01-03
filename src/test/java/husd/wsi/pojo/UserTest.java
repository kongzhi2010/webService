package husd.wsi.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testToString() throws Exception {
        User user = new User("name","pass","email");
        assertEquals("user is","user detail is :username:name,password:pass,email:email",user.toString());
    }
}