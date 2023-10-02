package ru.mipt.hsse.course1.basic.test;

public class Generics {
	public static Integer max(Integer a, Integer b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static String max(String a, String b) {
		if (a.compareTo(b) > 0) {
			return a;
		} else {
			return b;
		}
	}

	public static <T extends Comparable> T max3(T a, T b) {
		if (a.compareTo(b) > 0) {
			return a;
		} else {
			return b;
		}
	}

	public static Comparable max2(Comparable a, Comparable b) {
		if (a.compareTo(b) > 0) {
			return a;
		} else {
			return b;
		}
	}


	public static void main(String[] args) {
		Integer maxI = max3(1, 2);
		String maxS = max3("a", "b");
	}
}
