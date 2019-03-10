package com.wanghuan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhSpringBootApplicationTests {

	@Test
	public void contextLoads() {

		ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
		context.getBean("");

	}

}
