package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExecutorsExample implements Runnable {
	private static final Logger logger = Logger.getLogger(ExecutorsExample.class.getSimpleName());

	private final String name;

	public ExecutorsExample(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("Starting thread " + name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.warning("Thread interrupted");
			return;
		}
		logger.info("Finishing thread " + name);
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newCachedThreadPool();
//		var executor = Executors.newFixedThreadPool(3);
		executor.submit(new ExecutorsExample("first"));
		executor.submit(new ExecutorsExample("second"));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
}
