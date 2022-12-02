package ru.mipt.hsse.course1.jpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.mipt.hsse.course1.jpa.model.Order;
import ru.mipt.hsse.course1.jpa.model.User;

import java.util.List;

public interface OrdersRepositoryV2 extends PagingAndSortingRepository<Order, Integer> {
	List<Order> findByUser(User user, Pageable pageable);
}
