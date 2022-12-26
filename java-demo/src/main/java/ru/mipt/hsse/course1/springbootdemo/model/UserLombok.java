package ru.mipt.hsse.course1.springbootdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLombok {
	private final String login;
	private Integer value;
//	private String firstName;
//	private String lastName;
//	private boolean enabled;
//	private Integer blockTime;
//	private int authCount;

	@Builder(builderMethodName = "builder", toBuilder = true)
	public UserLombok newClient(String login, Integer x) {
		return new UserLombok(login, x);
	}
}
