package ru.mipt.hsse.course1.springbootdemo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLombokTest {

	@Test
	void lombokGetters() {
		UserLombok ul = new UserLombok("pasha");
		ul.setEnabled(true);

		assertTrue(ul.isEnabled());
	}

	@Test
	void lombokCtor() {
		UserLombok ul = new UserLombok("sasha");
		assertEquals("sasha", ul.getLogin());
	}

	@Test
	void lombokFabric() {
		UserLombok ul = UserLombok.of("test");
		assertEquals("max", ul.getLogin());
	}

	@Test
	void lombokEquals() {
		UserLombok ul1 = new UserLombok("sasha");
		ul1.setAuthCount(2);
		UserLombok ul2 = new UserLombok("sasha");
		assertNotEquals(ul1, ul2);
		ul2.setAuthCount(2);
		testClasses(ul1, ul2);
	}


	void testClasses(UserLombok l1, UserLombok l2) {
		assertEquals(l1, l2);
	}
}