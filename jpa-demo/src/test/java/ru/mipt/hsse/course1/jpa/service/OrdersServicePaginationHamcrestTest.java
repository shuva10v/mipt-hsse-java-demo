package ru.mipt.hsse.course1.jpa.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;
import ru.mipt.hsse.course1.jpa.repository.OrdersRepositoryV2;
import ru.mipt.hsse.course1.jpa.repository.UserRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
class OrdersServicePaginationHamcrestTest {

	@Autowired
	private OrdersServiceImpl ordersService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrdersRepositoryV2 orderRepository;


	private Item pizza = new Item();
	private Item drink = new Item();
	private Item cookies = new Item();

	@BeforeEach
	public void init() {
		User user = new User();
		user.setName("pasha");
		userRepository.save(user);
		pizza.setName("pizza");
		drink.setName("drink");
		cookies.setName("cookies");
		pizza = itemRepository.save(pizza);
		drink = itemRepository.save(drink);
		cookies = itemRepository.save(cookies);
	}

	@AfterEach
	public void cleanup() {
		orderRepository.deleteAll();
		itemRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testPagination() {
		User user = userRepository.findByName("pasha");
		ordersService.addToCart(user, pizza, 2);
		ordersService.addToCart(user, drink, 10);
		ordersService.addToCart(user, cookies, 20);

		assertThat(orderRepository.findByUser(user, Pageable.ofSize(1)), hasSize(1));
		assertThat(orderRepository.findByUser(user, Pageable.ofSize(2)), hasSize(2));
	}

	@Test
	public void testSorting() {
		User user = userRepository.findByName("pasha");
		ordersService.addToCart(user, pizza, 2);
		ordersService.addToCart(user, drink, 10);
		ordersService.addToCart(user, cookies, 20);

		List<Order> orders = orderRepository.findByUser(user, PageRequest.of(0, 2, Sort.by("count").descending()));
		assertThat(orders, hasSize(2));
		assertThat(orders.get(0).getCount(), equalTo(20));
		assertThat(orders.get(1).getCount(), equalTo(10));

		assertThat(orders, hasItem(hasProperty("count", equalTo(20))));

		assertThat(orders, hasItem(
				allOf(
						hasProperty("count", equalTo(20)),
						hasProperty("item",
								hasProperty("name", equalTo("cookies"))
						)
				)
		));
	}
}