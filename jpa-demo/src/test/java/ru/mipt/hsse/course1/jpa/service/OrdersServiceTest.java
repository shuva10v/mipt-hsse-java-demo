package ru.mipt.hsse.course1.jpa.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrderRepository;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class OrdersServiceTest {

	@Autowired
	private OrdersServiceImpl ordersService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	private Item pizza = new Item();
	private Item drink = new Item();

	@BeforeEach
	public void init() {
		User user = new User();
		user.setName("pasha");
		userRepository.save(user);
		pizza.setName("pizza");
		drink.setName("drink");
		pizza = itemRepository.save(pizza);
		drink = itemRepository.save(drink);
	}

	@AfterEach
	public void cleanup() {
		orderRepository.deleteAll();
		itemRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testAdd() {
//		assertEquals(1, userRepository.count());
		User user = userRepository.findByName("pasha");
		assertNotNull(user);
		assertEquals("pasha", user.getName());
		assertEquals(0, user.getOrders().size());
		ordersService.addToCart(user, pizza, 2);
		ordersService.addToCart(user, drink, 1);
		ordersService.addToCart(user, drink, 2);

		assertEquals(3, user.getOrders().size());
		user = userRepository.findByName("pasha");
		assertEquals(3, user.getOrders().size());
		assertEquals(3, orderRepository.count());
	}

	@Test
	public void testAddOrUpdate() {
		assertEquals(1, userRepository.count());
		User user = userRepository.findByName("pasha");
		assertNotNull(user);
		assertEquals("pasha", user.getName());
		assertEquals(0, user.getOrders().size());
		Order order1 = ordersService.addToCartOrUpdate(user, pizza, 2);
		Order order2 = ordersService.addToCartOrUpdate(user, drink, 1);
		Order order3 = ordersService.addToCartOrUpdate(user, drink, 2);

		assertEquals(2, orderRepository.count());
		assertEquals(2, user.getOrders().size());

		assertEquals(pizza, order1.getItem());
		assertEquals(drink, order2.getItem());
		assertEquals(drink, order3.getItem());
		assertEquals(2, order1.getCount());
		assertEquals(3, order2.getCount());
		assertEquals(3, order3.getCount());
	}

	@Test
	public void testUserNotFound() {
		assertEquals(1, userRepository.count());
		User user = userRepository.findByName("unknown");
		assertNull(user);
	}
}