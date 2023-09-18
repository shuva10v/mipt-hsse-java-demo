package ru.mipt.hsse.course1.basic.test.model;

public class Shelf implements FurnitureInterface, PriceAware{
	@Override
	public int area() {
		return 100;
	}

	@Override
	public int priceRub() {
		return 100;
	}
}
