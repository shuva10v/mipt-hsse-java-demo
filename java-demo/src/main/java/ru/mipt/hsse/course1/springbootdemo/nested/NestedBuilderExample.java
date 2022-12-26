package ru.mipt.hsse.course1.springbootdemo.nested;

public class NestedBuilderExample {
	private String value;

	public static class Builder {
		private NestedBuilderExample data = new NestedBuilderExample();

		private Builder value(String value) {
			data.value = value;
			return this;
		}

		public NestedBuilderExample build() {
			return data;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static void main(String[] args) {
		NestedBuilderExample.builder()
				.value("1212").build();
	}
}
