package ru.mipt.hsse.course1.basic.test.model;

public class Table extends RectangularFurniture {
	public static String name = "Table";

	public Table(int width, int height) {
		super(width, height);
	}

	public void enlarge(int width) {
		if (width > 0 && width < 1000) {
			this.width = width;
		} else {
			//
		}
	}
}
