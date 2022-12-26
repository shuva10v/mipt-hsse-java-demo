package ru.mipt.hsse.course1.springbootdemo.generics;

import java.util.List;

public class AdvancedReportService implements ReportService<AdvancedReport> {
	@Override
	public AdvancedReport generate(Long id) {
		return new AdvancedReport(List.of("222", "333"));
	}
}
