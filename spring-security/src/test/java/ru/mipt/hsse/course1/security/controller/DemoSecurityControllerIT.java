package ru.mipt.hsse.course1.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoSecurityControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "user1", roles = {"USER"})
	public void testSecurityUser() throws Exception {
		mockMvc.perform(get("/user"))
				.andExpect(status().isOk())
				.andExpect(content().string("USER: user1"));
		mockMvc.perform(get("/admin"))
				.andExpect(status().isForbidden());
	}


	@Test
	@WithMockUser(username = "user2", roles = {"USER", "ADMIN"})
	public void testSecurityAdmin() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(content().string("ADMIN: user2"));
	}
}