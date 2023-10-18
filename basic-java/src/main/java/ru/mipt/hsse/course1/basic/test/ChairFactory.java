package ru.mipt.hsse.course1.basic.test;

import ru.mipt.hsse.course1.basic.test.exceptions.FurnitureNotFoundException;
import ru.mipt.hsse.course1.basic.test.model.FurnitureInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChairFactory {
	private static Map<String, Supplier<FurnitureInterface>> suppliers = new HashMap<>();

	public static FurnitureInterface create(String name) throws FurnitureNotFoundException {
		var supplier = suppliers.get(name);
		if (supplier == null) {
			throw new FurnitureNotFoundException();
		} else {
			return supplier.get();
		}
	}

	public static void register(String name, Supplier<FurnitureInterface> supplier) {
		suppliers.put(name, supplier);
	}
}
