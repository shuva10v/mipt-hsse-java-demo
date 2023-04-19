package ru.mipt.hsse.course1.rabbitmq.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.mipt.hsse.course1.rabbitmq.configuration.BrokerConfiguration;

@Service
@Slf4j
public class TasksReceiver {
//	static final Counter requests = Counter.build()
//			.name("requests_total").help("Total requests.").register();
//
//	private static final Summary requestLatency = Summary.build()
//			.labelNames("queue")
//			.name("requests_latency_seconds")
//			.help("request latency in seconds")
//			.register();

	private final Counter requests;
	private final Counter errors;


	public TasksReceiver(MeterRegistry meterRegistry) {
		requests = meterRegistry.counter("demo_requests_total");
		errors = meterRegistry.counter("demo_errors");
	}
	
	@RabbitListener(queues = {BrokerConfiguration.QUEUE_NAME1, BrokerConfiguration.QUEUE_NAME2})
	public void receive(String text, @Header(AmqpHeaders.CONSUMER_QUEUE) String queueName,
						MessageHeaders headers) throws InterruptedException {
		requests.increment();
		log.info("Got new message: " + text + ", headers: " + headers.values());
		try {
			Long sleep = Long.parseLong(text);
			Thread.sleep(sleep);
		} catch (Exception e) {
			errors.increment();
		}
	}
//
//	@RabbitListener(queues = BrokerConfiguration.QUEUE_NAME, ackMode = "MANUAL")
//	public void receive(String text, MessageHeaders headers, Channel channel,
//						@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException, IOException {
//		log.info("Got new message: " + text + ", headers: " + headers.values());
//		if (new Random().nextBoolean()) {
//			Thread.sleep(1000);
//			channel.basicReject(tag, true);
//			log.info("Rejecting message");
//		} else {
//			channel.basicAck(tag, false);
//			log.info("Acknowledge");
//		}
//	}
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	@RabbitListener(queues = BrokerConfiguration.QUEUE_NAME, ackMode = "MANUAL")
//	public void receive(String text, MessageHeaders headers, Channel channel,
//						@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException, IOException {
//		log.info("Got new message: " + text + ", headers: " + headers.values());
//		if (new Random().nextBoolean()) {
//			Thread.sleep(1000);
//			channel.basicReject(tag, true);
//			log.info("Rejecting message");
//		} else {
//			if (new Random().nextBoolean()) {
//				log.info("Doubling message");
//				rabbitTemplate.convertAndSend(BrokerConfiguration.QUEUE_NAME, text + " " + text);
//			}
//			channel.basicAck(tag, false);
//			log.info("Acknowledge");
//		}
//	}
}

