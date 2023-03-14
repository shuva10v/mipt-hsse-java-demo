package ru.mipt.hsse.course1.feign.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class OrdersManager {
	private OrdersService ordersService;

	@Value("${orders.service.url:http://localhost:8081}")
	private String serviceUrl;

	@PostConstruct
	private void configure() {
		ordersService = Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(OrdersService.class, serviceUrl);
	}

	private void createRequest(Long userId, String itemName) {
		ordersService.add(OrdersService.AddRequestDto.builder()
						.userId(userId)
						.itemName(itemName)
						.count(10)
				.build());
	}

	public void testFeign(Long userId, String itemName) {
		var item = ordersService.createItem(itemName);
		log.info("Item created: " + item);
		createRequest(userId, itemName);
		for (var order : ordersService.listOrders()) {
			log.info("Item from server: " + order.getItem().getName() + ", count " + order.getCount());
		}
	}

	public void listUsers() {
		for (var item : ordersService.listUsers()) {
			log.info("Item from server: " + item.getName() + " with id " + item.getId());
		}
	}
}
