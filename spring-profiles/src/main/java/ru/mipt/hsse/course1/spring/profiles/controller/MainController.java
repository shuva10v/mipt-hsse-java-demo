package ru.mipt.hsse.course1.spring.profiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.hsse.course1.spring.profiles.service.EmailSenderService;

@Controller
public class MainController {
	@Autowired
	private EmailSenderService emailSenderService;

	@Value("${value:OK}")
	private String value;

	@RequestMapping(value="/test/{to}/{body}")
	@ResponseBody
	public String test(@PathVariable("to") String to, @PathVariable("body") String body) {
		emailSenderService.sendEmail(to, body);
		return value;
	}
}
