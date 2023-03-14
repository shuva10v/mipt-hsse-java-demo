package ru.mipt.hsse.course1.feign.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import lombok.Builder;
import lombok.Data;

import java.util.List;

interface OrdersService {
	@Data
	class ItemDto {
		 private Long id;
		 private String name;
	}
	@Data
	class OrderDto {
		private Long id;
		private Integer count;
		private ItemDto item;
	}

	@Data
	class UserDto {
		private Long id;
		private String name;
	}

	@Data
	@Builder
	class AddRequestDto {
		private Long userId;
		private String itemName;
		private Integer count;
	}

	@RequestLine("POST /add")
	@Headers("Content-Type: application/json")
	void add(AddRequestDto dto);

	@RequestLine("PUT /items/{itemName}")
	ItemDto createItem(@Param("itemName") String itemName);

	@RequestLine("GET /list")
	List<OrderDto> listOrders();

	@RequestLine("GET /users")
	List<UserDto> listUsers();
}
