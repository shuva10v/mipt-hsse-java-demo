package ru.mipt.hsse.course1.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	public static class InfoDto {
		public Long utcTime;
	}

	@GetMapping("/info")
	@ResponseBody
	public InfoDto getInfo() {
		InfoDto dto = new InfoDto();
		dto.utcTime = System.currentTimeMillis();
		return dto;
	}
}
