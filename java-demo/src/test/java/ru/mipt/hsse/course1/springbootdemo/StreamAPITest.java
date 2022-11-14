package ru.mipt.hsse.course1.springbootdemo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamAPITest {
	@Test
	public void testStreamsMapFilter() {
		final List<String> list = List.of("a", "x", "", "y", "maxim", "hello", "123", "b", "c");
		List<String> onlyDigits = list.stream().filter(new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.matches("\\d+");
			}
		}).collect(Collectors.toList());

		assertEquals(List.of("123"), onlyDigits);
		
		List<Integer> onlyDigitsInt = list.stream()
				.filter(s -> s.matches("\\d+"))
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		assertEquals(List.of(123), onlyDigitsInt);
	}

	@Test
	public void testWordsCount() {
		final List<String> texts = List.of(
			"In this example, widgets is a Collection<Widget>. We create a stream of Widget objects via Collection.stream(), filter it to produce a stream containing only the red widgets, and then transform it into a stream of int values representing the weight of each red widget. Then this stream is summed to produce a total weight",
			"In addition to Stream, which is a stream of object references, there are primitive specializations for IntStream, LongStream, and DoubleStream, all of which are referred to as \"streams\" and conform to the characteristics and restrictions described here.\n",
			"Most stream operations accept parameters that describe user-specified behavior, such as the lambda expression w -> w.getWeight() passed to mapToInt in the example above. To preserve correct behavior, these behavioral parameters:\n"
		);

		Long wordsCount = texts.stream()
				.flatMap(s -> Arrays.stream(s.split("\\W+")))
				.map(String::toLowerCase)
				.distinct()
				.count();
		assertEquals(75, wordsCount);
	}

	@Test
	public void mostPopularWord() {
		final List<String> texts = List.of(
				"In this example, widgets is a Collection<Widget>. We create a stream of Widget objects via Collection.stream(), filter it to produce a stream containing only the red widgets, and then transform it into a stream of int values representing the weight of each red widget. Then this stream is summed to produce a total weight",
				"In addition to Stream, which is a stream of object references, there are primitive specializations for IntStream, LongStream, and DoubleStream, all of which are referred to as \"streams\" and conform to the characteristics and restrictions described here.\n",
				"Most stream operations accept parameters that describe user-specified behavior, such as the lambda expression w -> w.getWeight() passed to mapToInt in the example above. To preserve correct behavior, these behavioral parameters:\n"
		);

		Map<String, Long> topWords = texts.stream()
				.flatMap(s -> Arrays.stream(s.split("\\W+")))
				.map(String::toLowerCase)
				.filter(w -> w.length() > 3)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Optional<String> topWord = topWords.entrySet().stream()
				.sorted(Comparator.comparing((Map.Entry<String, Long> entry) -> entry.getValue()).reversed())
				.map(entry -> entry.getKey())
				.findFirst();

		if (topWord.map(String::toLowerCase).isPresent()) {
			System.out.println(topWord.get());
		} else {
			System.out.println("None");
		}


		assertEquals(Optional.of("stream"), topWord);
				
	}

	
}
