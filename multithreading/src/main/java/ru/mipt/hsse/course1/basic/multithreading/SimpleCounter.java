package ru.mipt.hsse.course1.basic.multithreading;

public class SimpleCounter {
	private int count;
	public void increment() {
		int temp = count;
		count = temp + 1;
	}

	public int getCount() {
		return count;
	}
}
