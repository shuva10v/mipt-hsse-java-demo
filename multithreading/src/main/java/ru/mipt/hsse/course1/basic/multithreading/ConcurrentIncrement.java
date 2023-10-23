package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ConcurrentIncrement implements Runnable {
	private static final Logger logger = Logger.getLogger(ConcurrentIncrement.class.getSimpleName());

	private static int counter = 0;
	private static final Object lock = new Object();

	private final int iterations;
	private final String name;

	public ConcurrentIncrement(String name, int iterations) {
		this.iterations = iterations;
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("Starting thread " + name + ", counter value: " + counter);
		for (int i = 0; i < iterations; i++) {
			synchronized (lock) {
				ConcurrentIncrement.counter += 1;
			}
		}
		logger.info("Finishing thread " + name + ", counter value: " + counter);
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newFixedThreadPool(2);
		executor.submit(new ConcurrentIncrement("first", 100000));
		executor.submit(new ConcurrentIncrement("second", 100000));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		logger.info("Counter value after shutdown: " + ConcurrentIncrement.counter);
	}
}
