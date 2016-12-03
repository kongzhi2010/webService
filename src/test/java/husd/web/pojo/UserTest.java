package husd.web.pojo;

import husd.web.model.UserProfile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testToString() throws Exception {
        UserProfile user = new UserProfile("name","pass","email");
        assertEquals("user is","user detail is :username:name,password:pass,email:email",user.toString());
    }
}