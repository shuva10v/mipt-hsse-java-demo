package ru.mipt.hsse.course1.springbootdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mipt.hsse.course1.springbootdemo.model.UserLombok;

@Configuration
public class AppConfiguration {
	@Bean
	public UserLombok createUser() {
		return null;
//		return new UserLombok();
	}
}
