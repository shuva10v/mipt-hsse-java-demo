package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SemaphoresExample implements Runnable {
	private static final Logger logger = Logger.getLogger(SemaphoresExample.class.getSimpleName());

	private final Semaphore semaphore;
	private final String name;

	public SemaphoresExample(String name, Semaphore semaphore) {
		this.semaphore = semaphore;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			logger.info("Starting thread " + name);
			semaphore.acquire();
			logger.info("Acquired " + name);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		} finally {
			semaphore.release();
		}


		logger.info("Finishing thread " + name);
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newFixedThreadPool(5);
		var semaphore = new Semaphore(2);
		executor.submit(new SemaphoresExample("first", semaphore));
		executor.submit(new SemaphoresExample("second", semaphore));
		executor.submit(new SemaphoresExample("third", semaphore));
		executor.submit(new SemaphoresExample("fourth", semaphore));

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
}
