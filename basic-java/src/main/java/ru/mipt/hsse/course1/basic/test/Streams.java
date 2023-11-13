package ru.mipt.hsse.course1.basic.test;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Streams {
	private static final Logger logger = Logger.getLogger(Streams.class.getSimpleName());

	public static void listTransform() {
		List<Integer> digits = List.of(1, 2, 3, 4, 5, 6);
		var res = digits.stream()
				.map(integer -> integer * integer)
				.map(Objects::toString)
				.collect(Collectors.toList());
		logger.info("Result is: " + res);
	}

	public static void listFilter() {
		List<Integer> digits = List.of(1, 2, 3, 4, 5, 6);
		var res = digits.stream().map(i -> i * i)
				.filter(i -> i % 2 == 0)
				.map(i -> i.toString()).collect(Collectors.toList());
		logger.info("Result is: " + res);
	}

	public static void findExample() {
		List<Integer> digits = List.of(1, 2, 3, 4, 5, 6);
		var res = digits.stream()
				.map(i -> i * i)
				.filter(i -> i % 2 == 0).findFirst();
		logger.info("Result is: " + res.isPresent());
	}

	public static void mapProducer() {
		List<Integer> digits = List.of(1, 2, 3, 4, 5, 6);
		var res = digits.stream()
						.collect(Collectors.toMap(Function.identity(), i -> i * i));
		logger.info("Result is: " + res);
	}

	public static void randomProducer() {
		var randomNumbers = new Random().ints(1000, 0, 1000);
		var res = randomNumbers.distinct().count();
		logger.info("Result is: " + res);
	}

	public static void main(String[] args) {
		randomProducer();
	}
}
