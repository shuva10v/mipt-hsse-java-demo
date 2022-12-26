package ru.mipt.hsse.course1.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrderRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;

import java.util.Optional;

@Controller
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Override
	@Transactional
	public Order addToCart(User user, Item item, int count) {
		Order order = new Order();
		order.setUser(user);
		order.setItem(item);
		order.setCount(count);
		user.getOrders().add(order);
		userRepository.save(user);
		orderRepository.save(order);
		return order;
	}

	@Override
	@Transactional
	public Order addToCartOrUpdate(User user, Item item, int count) {
		Optional<Order> maybeOrder = user.getOrders().stream()
				.filter(o -> o.getItem().equals(item)).findFirst();
		Order order = maybeOrder.orElseGet(() -> {
					Order newOrder = new Order();
					newOrder.setUser(user);
					newOrder.setItem(item);
					newOrder.setCount(0);
					return orderRepository.save(newOrder);
				});
		order.setCount(order.getCount() + count);
		if (maybeOrder.isEmpty()) {
			user.getOrders().add(order);
		}
		// optional
		userRepository.save(user);
		orderRepository.save(order);
		return order;
	}
}
