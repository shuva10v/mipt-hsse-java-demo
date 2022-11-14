package ru.mipt.hsse.course1.springbootdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLombokBuilder {
	private final String login;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private Integer blockTime;
	private int authCount;
}
