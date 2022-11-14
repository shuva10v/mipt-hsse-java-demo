package ru.mipt.hsse.course1.springbootdemo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

	@Test
	void recordEquals() {
		UserLombok ul1 = new UserLombok("sasha");
		ul1.setAuthCount(2);
		UserLombok ul2 = new UserLombok("sasha");
		assertNotEquals(ul1, ul2);
		ul2.setAuthCount(2);
		assertEquals(ul2, ul2);
	}
}