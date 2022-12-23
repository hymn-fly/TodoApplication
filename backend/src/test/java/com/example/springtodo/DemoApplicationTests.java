package com.example.springtodo;

import com.example.springtodo.security.JwtConfigure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private JwtConfigure jwtConfigure;

	@Test
	void test() {
		System.out.println(jwtConfigure.getIssuer());
	}
}
