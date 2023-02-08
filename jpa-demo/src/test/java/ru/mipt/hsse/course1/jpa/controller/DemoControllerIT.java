package ru.mipt.hsse.course1.jpa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.User;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrderRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	private User testUser;

	@BeforeEach
	public void init() {
		itemRepository.deleteAll();
		testUser = new User();
		testUser.setName("max");
		testUser = userRepository.save(testUser);
		Item pizza = new Item();
		pizza.setName("pizza");
		itemRepository.save(pizza);
	}

	@Test
	public void testAddAndGet() throws Exception {
		mockMvc.perform(post("/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{"userId": %s, "itemName": "pizza", "count":  5}
					""".formatted(testUser.getId()))).andExpect(status().isOk());
		assertThat(orderRepository.findAll(), hasItem(
				allOf(
						hasProperty("item", hasProperty("name", equalTo("pizza"))),
						hasProperty("count", equalTo(5))
				)));

		var result = mockMvc.perform(get("/list")).andExpect(status().isOk()).andReturn();
		var content = result.getResponse().getContentAsString();
		assertThat(content, containsString("pizza"));
	}
}