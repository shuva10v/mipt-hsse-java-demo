package ru.mipt.hsse.course1.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mipt.hsse.course1.feign.service.OrdersManager;

@Component
@Slf4j
public class OrdersCli implements CommandLineRunner {
	@Autowired
	private OrdersManager manager;
	@Override
	public void run(String... args) throws Exception {
		log.info("Running CLI tool");
		if (args.length > 1) {
			manager.testFeign(Long.parseLong(args[0]), args[1]);
		} else {
			manager.listUsers();
		}

	}
}
