package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

	public class AtomicIncrements implements Runnable {
	private static final Logger logger = Logger.getLogger(AtomicIncrements.class.getSimpleName());

	private static AtomicInteger counter = new AtomicInteger(0);

	private final int iterations;
	private final String name;

	public AtomicIncrements(String name, int iterations) {
		this.iterations = iterations;
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("Starting thread " + name + ", counter value: " + counter);
		for (int i = 0; i < iterations; i++) {
			AtomicIncrements.counter.incrementAndGet();
		}
		logger.info("Finishing thread " + name + ", counter value: " + counter);
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newFixedThreadPool(2);
		executor.submit(new AtomicIncrements("first", 100000));
		executor.submit(new AtomicIncrements("second", 100000));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		logger.info("Counter value after shutdown: " + AtomicIncrements.counter.get());
	}
}
