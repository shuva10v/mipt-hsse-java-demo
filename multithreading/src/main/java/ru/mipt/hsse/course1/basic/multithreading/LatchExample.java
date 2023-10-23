package ru.mipt.hsse.course1.basic.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LatchExample {
	private static final Logger logger = Logger.getLogger(LatchExample.class.getSimpleName());

	public static class Worker implements Runnable {
		private final CountDownLatch latch;
		private final String name;

		public Worker(String name, CountDownLatch latch) {
			this.latch = latch;
			this.name = name;
		}

		@Override
		public void run() {
			logger.info("Running worker " + name);
			while (latch.getCount() > 0) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
				logger.info("Job done by " + name);
				latch.countDown();
			}
		}
	}

	public static class Manager implements Runnable {
		private final CountDownLatch latch;

		public Manager(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			logger.info("Running manager");
			try {
				latch.await();
				logger.info("Jobs done, victory!");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newCachedThreadPool();
		var latch = new CountDownLatch(10);
		executor.submit(new Worker("first", latch));
		executor.submit(new Worker("second", latch));
		executor.submit(new Manager(latch));

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
	}
}
