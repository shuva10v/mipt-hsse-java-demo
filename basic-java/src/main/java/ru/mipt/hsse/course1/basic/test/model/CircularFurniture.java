package ru.mipt.hsse.course1.basic.test.model;

abstract public class CircularFurniture implements FurnitureInterface {
	protected int radius;

	public CircularFurniture(int radius) {
		this.radius = radius;
	}

	public int area() {
		return (int) (radius * radius * Math.PI);
	}
}
