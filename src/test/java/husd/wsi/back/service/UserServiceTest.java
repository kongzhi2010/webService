package husd.wsi.back.service;

import static org.junit.Assert.*;
import husd.wsi.back.pojo.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring-mvc.xml") 
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindUser() {
		User excepted = userService.findUser("husd");
		assertEquals("husd", excepted.getUsername());
		assertEquals("1234566", excepted.getPassword());
		//assertEquals("123@qq.com", excepted.getEmail());
	}
	
	@Test
	public void testSaveUser(){
		User user = new User();
		user.setUsername("test1");
		user.setPassword("123456");
		user.setEmail("123@qq.com");
		userService.saveUser(user);
	}

}
