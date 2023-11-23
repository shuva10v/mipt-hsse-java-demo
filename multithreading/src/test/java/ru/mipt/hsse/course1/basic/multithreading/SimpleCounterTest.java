package ru.mipt.hsse.course1.basic.multithreading;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCounterTest {
	@Test
	public void testCounter() {
		SimpleCounter counter = new SimpleCounter();
		for (int i = 0; i < 500; i++) {
			counter.increment();
		}
		assertEquals(500, counter.getCount());
	}

	@Test
	public void testCounterWithConcurrency() throws InterruptedException {
		int numberOfThreads = 10000;
		ExecutorService service = Executors.newFixedThreadPool(10);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		SimpleCounter counter = new SimpleCounter();
		for (int i = 0; i < numberOfThreads; i++) {
			service.execute(() -> {
				counter.increment();
				latch.countDown();
			});
		}
		latch.await();
		assertEquals(numberOfThreads, counter.getCount());
	}
}