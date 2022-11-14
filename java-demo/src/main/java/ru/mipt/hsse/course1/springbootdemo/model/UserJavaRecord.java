package ru.mipt.hsse.course1.springbootdemo.model;

record UserJavaRecord (
		String login,
		String firstName,
		String lastName,
		boolean enabled,
		Integer blockTime,
		int authCount
) {
	public UserJavaRecord(String login) {
		this(login, null, null, false, null, 0);
	}
}
