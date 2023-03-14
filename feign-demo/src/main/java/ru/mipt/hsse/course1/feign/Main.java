package ru.mipt.hsse.course1.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}