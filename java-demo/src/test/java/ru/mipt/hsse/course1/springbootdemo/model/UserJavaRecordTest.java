package ru.mipt.hsse.course1.springbootdemo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserJavaRecordTest {

	@Test
	void recordGetters() {
		UserJavaRecord ul = new UserJavaRecord("pasha");
		assertFalse(ul.enabled());
		assertEquals("pasha", ul.login());
	}

	@Test
	void recordCtor() {
		UserJavaRecord ul = new UserJavaRecord("sasha");
		assertEquals("sasha", ul.login());
	}
}