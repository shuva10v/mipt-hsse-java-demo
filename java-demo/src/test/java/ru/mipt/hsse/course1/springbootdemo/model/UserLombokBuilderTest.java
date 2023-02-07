package ru.mipt.hsse.course1.springbootdemo.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLombokBuilderTest {
	private static Integer value;

	@BeforeAll
	public static void init() {
		value = 100;
	}

	

	@Order(0)
	@Test
	public void builderTest() {
		UserLombokBuilder user = UserLombokBuilder.builder()
				.login("pasha")
				.authCount(10)
				.build();

		assertEquals(10, user.getAuthCount());
		assertEquals("pasha", user.getLogin());
	}

}