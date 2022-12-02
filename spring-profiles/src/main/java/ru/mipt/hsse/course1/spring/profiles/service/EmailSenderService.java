package ru.mipt.hsse.course1.spring.profiles.service;

public interface EmailSenderService {
	void sendEmail(String to, String message);
}
