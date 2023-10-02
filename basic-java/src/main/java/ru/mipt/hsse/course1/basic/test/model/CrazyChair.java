package ru.mipt.hsse.course1.basic.test.model;

import java.util.Random;

public class CrazyChair extends Chair {
	public CrazyChair(int width, int height) {
		super(width, height);
	}

	@Override
	public int area() {
		return (int) (super.area() * (new Random().nextDouble()));
	}
}
