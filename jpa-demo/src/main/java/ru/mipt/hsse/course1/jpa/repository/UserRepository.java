package ru.mipt.hsse.course1.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mipt.hsse.course1.jpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
