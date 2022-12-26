package ru.mipt.hsse.course1.springbootdemo.generics;

public class SimpleReportService implements ReportService<SimpleReport> {
	@Override
	public SimpleReport generate(Long id) {
		return new SimpleReport("1221");
	}
}
