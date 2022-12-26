package ru.mipt.hsse.course1.springbootdemo.generics;

import java.util.List;

public class AdvancedReport implements Report {
	private final List<String> data;

	public AdvancedReport(List<String> data) {
		this.data = data;
	}

	public List<String> getData() {
		return data;
	}

	@Override
	public String getName() {
		return "advanced";
	}

	@Override
	public String serialize() {
		return null;
	}
}
