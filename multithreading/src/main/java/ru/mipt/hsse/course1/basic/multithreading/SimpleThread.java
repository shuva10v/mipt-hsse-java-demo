package ru.mipt.hsse.course1.basic.multithreading;

import java.util.logging.Logger;

public class SimpleThread implements Runnable {
	private static final Logger logger = Logger.getLogger(SimpleThread.class.getSimpleName());

	private final String name;

	public SimpleThread(String name) {
		this.name = name;
	}


	@Override
	public void run() {
		logger.info("Starting thread " + name);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.warning("Thread interrupted");
			return;
		}
		logger.info("Finishing thread " + name);
	}

	public static void main(String[] args) {
		var t1 = new Thread(new SimpleThread("first"));
		var t2 = new Thread(new SimpleThread("second"));
		t1.start();
		t2.start();
	}
}
