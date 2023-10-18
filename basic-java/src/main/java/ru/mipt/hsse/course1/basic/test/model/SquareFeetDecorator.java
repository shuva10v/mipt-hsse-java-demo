package ru.mipt.hsse.course1.basic.test.model;

public class SquareFeetDecorator implements FurnitureInterface {
	public static final float CONVERSTION_COEFFICIENT = 10.7f;
	private final FurnitureInterface delegate;

	public SquareFeetDecorator(FurnitureInterface delegate) {
		this.delegate = delegate;
	}

	@Override
	public int area() {
		return Math.round(delegate.area() * CONVERSTION_COEFFICIENT);
	}
}
