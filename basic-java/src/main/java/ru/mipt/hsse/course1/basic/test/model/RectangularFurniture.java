package ru.mipt.hsse.course1.basic.test.model;

abstract public class Furniture {
	protected int width;
	protected int height;

	public Furniture(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int area() {
		return width * height;
	}
}
