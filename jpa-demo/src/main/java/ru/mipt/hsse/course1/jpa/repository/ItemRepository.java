package ru.mipt.hsse.course1.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mipt.hsse.course1.jpa.model.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByName(String name);
}
