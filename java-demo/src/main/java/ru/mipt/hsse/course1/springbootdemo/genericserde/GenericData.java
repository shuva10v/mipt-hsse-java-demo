package ru.mipt.hsse.course1.springbootdemo.genericserde;

import java.util.List;
import java.util.Objects;

public class GenericData<T> {

	public String inferType() {
		return data.get(0).getClass().getSimpleName();
	}

	public GenericData() {
		
	}

	public GenericData(List<T> data) {
		this.data = data;
	}

	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GenericData<?> that = (GenericData<?>) o;
		return Objects.equals(data, that.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}
}
