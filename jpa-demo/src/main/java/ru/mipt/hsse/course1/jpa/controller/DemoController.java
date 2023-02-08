package ru.mipt.hsse.course1.jpa.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrderRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;
import ru.mipt.hsse.course1.jpa.service.OrdersService;

import java.util.List;

@Controller
public class DemoController {
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderRepository orderRepository;

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
	@ResponseBody
	public String add(@RequestBody AddRequest request) {
		userRepository.findById(request.userId).ifPresent(user -> {

			ordersService.addToCart(user, itemRepository.findByName(request.itemName).get(), request.count);
		});
		return "OK";
	}

	@GetMapping("/list")
	@ResponseBody
	public List<Order> list() {
		return orderRepository.findAll();
	}
}
