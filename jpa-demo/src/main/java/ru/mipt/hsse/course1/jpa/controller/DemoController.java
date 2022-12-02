package ru.mipt.hsse.course1.jpa.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;
import ru.mipt.hsse.course1.jpa.service.OrdersService;

@Controller
public class DemoController {
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Data
	private static class AddRequest {
		private Long userId;
		private String itemName;
		private Integer count;
	}

	@PostMapping("/add")
	public String add(@RequestBody AddRequest request) {
		userRepository.findById(request.userId).ifPresent(user -> {

			ordersService.addToCart(user, itemRepository.findByName(request.itemName).get(), request.count);
		});
		return "OK";
	}
}
