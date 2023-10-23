package ru.mipt.hsse.course1.basic.multithreading;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ConcurrentMap implements Runnable {
	private static final Logger logger = Logger.getLogger(ConcurrentMap.class.getSimpleName());

	private static final Map<String, Integer> map = new ConcurrentHashMap<>();

	private final int iterations;
	private final String name;

	public ConcurrentMap(String name, int iterations) {
		this.iterations = iterations;
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("Starting thread " + name + ", map size: " + map.size());

		for (int i = 0; i < iterations; i++) {
			ConcurrentMap.map.put(name + i, i);
		}
		logger.info("Finishing thread " + name + ", map size: " + map.size());
	}

	public static void main(String[] args) throws InterruptedException {
		var executor = Executors.newFixedThreadPool(2);
		executor.submit(new ConcurrentMap("first", 100000));
		executor.submit(new ConcurrentMap("second", 100000));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		logger.info("Counter value after shutdown: " + ConcurrentMap.map.size());
	}
}
