package ru.mipt.hsse.course1.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoSecurityController {
	public record AuthInfoDTO(String name, String kind) {}

	@GetMapping("/user")
	@ResponseBody
	@Secured("ROLE_USER")
	public String userEndpoint(Authentication auth) {
		return "USER: " + auth.getName();
	}

	@GetMapping("/admin")
	@ResponseBody
	@Secured("ROLE_ADMIN")
	public String adminEndpoint(Authentication auth) {
		return "ADMIN: " + auth.getName();
	}
}
