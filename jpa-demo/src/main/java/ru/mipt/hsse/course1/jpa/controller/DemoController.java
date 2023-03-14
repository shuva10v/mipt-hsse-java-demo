package ru.mipt.hsse.course1.jpa.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrderRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;
import ru.mipt.hsse.course1.jpa.service.OrdersService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

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

	@PostConstruct
	private void configure() {
		User user = new User();
		user.setName("1221");
		user.setId(233l);
		userRepository.save(user);
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

	@GetMapping("/users")
	@ResponseBody
	public List<User> users() {
		return userRepository.findAll();
	}

	@PutMapping("/items/{itemName}")
	@ResponseBody
	public Item createItem(@PathVariable String itemName) {
		Optional<Item> maybeItem = itemRepository.findByName(itemName);
		if (maybeItem.isPresent()) {
			return maybeItem.get();
		}
		Item item = new Item();
		item.setName(itemName);
		return itemRepository.save(item);
	}
}
