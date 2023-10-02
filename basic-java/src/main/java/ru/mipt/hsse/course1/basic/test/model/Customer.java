package ru.mipt.hsse.course1.basic.test.model;

public class Customer {
	// TODO comment
	private int maxSize;

	public Customer(int maxSize) {
		this.maxSize = maxSize;
	}

	public void buy(FurnitureInterface furniture) {
		if (furniture.area() < maxSize) {
			// lets buy
		}
	}

//	public void buy(Object furniture) {
//		if (furniture instanceof Table) {
//			Table t = (Table) furniture;
//			//
//			///
//		}
//		if (furniture instanceof Chair) {
//			Chair c = (Chair) furniture;
//			///
//		}
////		if (table.area() < maxSize) {
////			// lets buy
////		}
//	}
}
