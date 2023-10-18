package ru.mipt.hsse.course1.basic.test.exceptions;

public class FurnitureNotFoundException2 extends Exception {
	public int code;
	public FurnitureNotFoundException2(String message, int code) {
		super(message);
		this.code = code;
	}
}
