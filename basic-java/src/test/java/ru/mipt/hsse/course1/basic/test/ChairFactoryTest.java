package ru.mipt.hsse.course1.basic.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.hsse.course1.basic.test.exceptions.FurnitureNotFoundException;
import ru.mipt.hsse.course1.basic.test.model.Chair;

import static org.junit.jupiter.api.Assertions.*;

class ChairFactoryTest {
	// @BeforeEach when using non-static fields!
	@BeforeAll
	public static void prepare() {
		ChairFactory.register("chair", () -> new Chair(100, 200));
	}

	@Test
	public void testCreateItem() throws FurnitureNotFoundException {
		var chair = ChairFactory.create("chair");
		assertEquals(Chair.class, chair.getClass());
		assertEquals(20000, chair.area());
	}

	@Test
	public void testItemNotFound() throws FurnitureNotFoundException {
		assertThrows(FurnitureNotFoundException.class, () -> ChairFactory.create("ottoman"));
	}
}