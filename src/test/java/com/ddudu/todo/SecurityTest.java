package com.ddudu.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityTest {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void encoding(){
		String password = "1234";
		String encodedPassword = passwordEncoder.encode(password);
		Assertions.assertThat(passwordEncoder.matches(password, encodedPassword)).isEqualTo(true);
	}

}
