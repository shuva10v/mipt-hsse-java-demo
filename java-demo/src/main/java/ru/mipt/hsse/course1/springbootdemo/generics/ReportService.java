package ru.mipt.hsse.course1.springbootdemo.generics;

public interface ReportService<T extends Report> {
	T generate(Long id);
}
