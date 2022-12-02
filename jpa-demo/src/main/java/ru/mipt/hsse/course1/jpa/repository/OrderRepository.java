package ru.mipt.hsse.course1.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mipt.hsse.course1.jpa.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
