package ru.mipt.hsse.course1.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {
	public static final String QUEUE_NAME1 = "tasks_queue1";
	public static final String QUEUE_NAME2 = "tasks_queue2";
	@Bean
	public Queue queue1() {
		return new Queue(QUEUE_NAME1, true);
	}

	@Bean
	public Queue queue2() {
		return new Queue(QUEUE_NAME2, true);
	}

}
