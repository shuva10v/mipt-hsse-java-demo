package ru.mipt.hsse.course1.springbootdemo.generics;

import java.util.List;
import java.util.stream.Collectors;

public class ReportManager {
	private AdvancedReportService advancedReportService;
	private SimpleReportService simpleReportService;

	public void process(Long id) {
		AdvancedReport report1 = advancedReportService.generate(id);
		SimpleReport report2 = simpleReportService.generate(id);
		List<String> processed = report1.getData().stream()
				.map(i -> i + report2.getValue())
				.collect(Collectors.toList());
		//
	}
}
