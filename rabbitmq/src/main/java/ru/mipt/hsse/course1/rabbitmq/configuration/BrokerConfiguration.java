package ru.mipt.hsse.course1.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {
	public static final String QUEUE_NAME = "tasks_queue";
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

}
