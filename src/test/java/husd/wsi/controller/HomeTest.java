package husd.wsi.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc.xml")
public class HomeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowHomePage() {
		Home home = new Home();
		Map<String, Object> model = new HashMap<String, Object>();
		assertEquals("this is a demo", model.get("demo"));
	}

}
