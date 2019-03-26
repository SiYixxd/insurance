package com.wanghuan;

import com.wanghuan.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhSpringBootApplicationTests {

	@Test
	public void contextLoads() throws Exception{

		List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
				"Date and Time API");
		features.forEach(n -> System.out.println(n));
		int count = 0;
		features.forEach(n -> {});

//		for(int i =0; i < features.size();i ++){
//			System.out.println(features.get(i));
//		}



	}

	@Test
	public void password() throws Exception{
		//第一种情况  保存密码
		String password = MD5Util.encrypt("18835579392" + "qwer1234");
		//保存到数据库
		//第二种情况， 登陆
		// 数据库存在的密码
		String dbPassword = password;
		String loginPassword = "qwer1234";
		String encoryPassword = MD5Util.encrypt("18835579392" + loginPassword);
		if(dbPassword.equals(encoryPassword)){
			//密码正确
		}else {
			//密码错误
		}
		//第三种情况 更改密码
		String oldPassword= "qwer1234";
		String newPassword= "qwer1234";

	}


}
