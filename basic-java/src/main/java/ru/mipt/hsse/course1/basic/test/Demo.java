package ru.mipt.hsse.course1.basic.test;

import ru.mipt.hsse.course1.basic.test.exceptions.FurnitureNotFoundException;
import ru.mipt.hsse.course1.basic.test.model.Chair;
import ru.mipt.hsse.course1.basic.test.model.Customer;
import ru.mipt.hsse.course1.basic.test.model.SquareFeetDecorator;
import ru.mipt.hsse.course1.basic.test.model.Table;

public class Demo {

	public static void registry() throws FurnitureNotFoundException {
		ChairFactory.register("table", () -> new Table(100, 200));
		ChairFactory.register("chair", () -> new Chair(100, 200));

		var furniture1 = ChairFactory.create("table");
		var furniture2 = ChairFactory.create("chair");

		System.out.println(furniture1);
		System.out.println(furniture2);
	}

	public static void decorator() throws FurnitureNotFoundException {
		var t = new Table(100, 200);
		System.out.println(t.area());
		var wrapper = new SquareFeetDecorator(t);
		System.out.println(wrapper.area());
	}

	public static void simpleActions() {
		Table t = new Table(100, 200);
		System.out.println(Table.name);
		t.enlarge(500);
		System.out.println(t.area());

		Chair chair = new Chair(100, 300);

		Customer customer = new Customer(10000);
		customer.buy(t);
		customer.buy(chair);
	}

	public static void main(String[] args) throws FurnitureNotFoundException {
		registry();
	}
}
