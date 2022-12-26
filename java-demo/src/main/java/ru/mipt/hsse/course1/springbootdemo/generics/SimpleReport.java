package ru.mipt.hsse.course1.springbootdemo.generics;

public class SimpleReport implements Report {
	private final String value;

	public SimpleReport(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String getName() {
		return "simple";
	}

	@Override
	public String serialize() {
		return null;
	}
}
