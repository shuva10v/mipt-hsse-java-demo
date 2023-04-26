package ru.mipt.hsse.course1.memoryleak.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {
	private List<String> products = new ArrayList<>();

	@GetMapping("test/{limit}")
	@ResponseBody
	public int test(@PathVariable("limit") int limit) {
		for (int i = 0; i< limit; i++){
			products.add(new String(new char[1024*1000])); // 1 MB string
		}
		return products.size();
	}
}
