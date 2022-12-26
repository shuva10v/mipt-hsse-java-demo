package ru.mipt.hsse.course1.springbootdemo.nested;

public class InnerBuilderExample {
	private String value;

	public class Builder {
		private Builder value(String value) {
			InnerBuilderExample.this.value = value;
			return this;
		}

		public InnerBuilderExample build() {
			return InnerBuilderExample.this;
		}
	}

	public static Builder builder() {
		return new InnerBuilderExample().new Builder();
	}

	public static void main(String[] args) {
		InnerBuilderExample.builder()
				.value("1212").build();
	}
}
