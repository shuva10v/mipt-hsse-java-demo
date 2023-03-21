package ru.mipt.hsse.course1.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import ru.mipt.hsse.course1.rabbitmq.configuration.BrokerConfiguration;

@Service
@Slf4j
public class TasksReceiver {
	@RabbitListener(queues = BrokerConfiguration.QUEUE_NAME)
	public void receive(String text, MessageHeaders headers) throws InterruptedException {
		log.info("Got new message: " + text + ", headers: " + headers.values());
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

