package ru.mipt.hsse.course1.basic.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Collections {
	public class Money {
		public int amount;
		public String currency;

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Money money = (Money) o;
			return amount == money.amount && Objects.equals(currency, money.currency);
		}

		@Override
		public int hashCode() {
			return Objects.hash(amount, currency);
		}
	}

	public static void main(String[] args) {
		List<Integer> ints = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			ints.add(i);
			// ints.set(i, i); // bad idea
		}

		for (Integer value: ints) {
			System.out.println(value);
		}

		LinkedList<String> str = new LinkedList<>();
		String current = "A";
		while (current.length() < 20) {
			current += "A";
			str.add(current);
		}

		String last = str.pollLast();

		for (String value: str) {
			System.out.println(value);
		}

		Set<Integer> randoms = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			randoms.add(new Random().nextInt(10));
		}

		for (Integer value: randoms) {
			System.out.println(value);
		}

		TreeSet<Integer> treeset = new TreeSet<>();
		for (int i = 0; i < 10; i++) {
			treeset.add(new Random().nextInt(10));
		}

		System.out.println(treeset.pollLast());

		Map<String, String> codes = new HashMap<>();
		codes.put("RU", "Russia");
		codes.put("KZ", "Kazakhstan");
		System.out.println(codes.get("RU"));
		System.out.println(codes.getOrDefault("UZ", "Unknown"));
		System.out.println(codes.containsKey("RU"));

		codes.remove("KZ");
		for (Map.Entry<String, String> item: codes.entrySet()) {
			System.out.println(item.getKey() + " => " + item.getValue());
		}
	}
}
