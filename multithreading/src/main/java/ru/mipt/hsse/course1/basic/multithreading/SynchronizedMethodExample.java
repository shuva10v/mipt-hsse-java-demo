package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SynchronizedMethodExample implements Runnable {
	private static final Logger logger = Logger.getLogger(SynchronizedMethodExample.class.getSimpleName());

	public static class Counter {
		private Integer value;

		public Counter(Integer value) {
			this.value = value;
		}

		synchronized public void increment() {
			value += 1;
		}

		synchronized public Integer get() {
			return value;
		}
	}

	private final int iterations;
	private final String name;
	private final Counter counter;

	public SynchronizedMethodExample(String name, int iterations, Counter counter) {
		this.iterations = iterations;
		this.name = name;
		this.counter = counter;
	}


	@Override
	public void run() {
		logger.info("Starting thread " + name + ", counter value: " + counter.get());
		for (int i = 0; i < iterations; i++) {
			this.counter.increment();
		}
		logger.info("Finishing thread " + name + ", counter value: " + counter.get());
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newFixedThreadPool(2);
		var counter = new Counter(0);
		executor.submit(new SynchronizedMethodExample("first", 100000, counter));
		executor.submit(new SynchronizedMethodExample("second", 100000, counter));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		logger.info("Counter value after shutdown: " + counter.get());
	}
}
