package ru.mipt.hsse.course1.basic.test.model;

abstract public class RectangularFurniture implements FurnitureInterface {
	protected int width;
	protected int height;

	public RectangularFurniture(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int area() {
		return width * height;
	}
}
