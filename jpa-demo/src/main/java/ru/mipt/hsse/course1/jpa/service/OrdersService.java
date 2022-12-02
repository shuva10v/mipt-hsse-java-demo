package ru.mipt.hsse.course1.jpa.service;

import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;

public interface OrdersService {
	Order addToCart(User user, Item item, int count);

	Order addToCartOrUpdate(User user, Item item, int count);
}
