package ru.mipt.hsse.course1.basic.test;

import ru.mipt.hsse.course1.basic.test.model.Chair;
import ru.mipt.hsse.course1.basic.test.model.Customer;
import ru.mipt.hsse.course1.basic.test.model.Table;

public class Demo {
//	public static void test(Table t) {
//		t.height = 100;
//	}

	public static void test2(int height) {
		height = 100;
	}

	public static void main(String[] args) {
		Table t = new Table(100, 200);
		System.out.println(Table.name);
		t.enlarge(500);
		System.out.println(t.area());

		Chair chair = new Chair(100, 300);

		Customer customer = new Customer(10000);
		customer.buy(t);
		customer.buy(chair);

//		System.out.println(t.height);
//		test(t);
//		System.out.println(t.height);

//		int height = 0;
//		System.out.println(height);
//		test2(height);
//		System.out.println(height);
	}
}
