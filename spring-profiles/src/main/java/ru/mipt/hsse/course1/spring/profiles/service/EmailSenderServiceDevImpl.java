package ru.mipt.hsse.course1.spring.profiles.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderServiceDevImpl implements EmailSenderService {
	@Override
	public void sendEmail(String to, String message) {
		log.info("sendEmail: " + to + " => '" + message + "'");
	}
}
