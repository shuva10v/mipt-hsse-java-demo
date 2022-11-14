package ru.mipt.hsse.course1.springbootdemo.model;

import lombok.Data;

@Data(staticConstructor = "of")
public class UserLombok<T> {
	private final String login;
	private T value;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private Integer blockTime;
	private int authCount;
}
