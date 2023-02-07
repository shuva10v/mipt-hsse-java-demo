package ru.mipt.hsse.course1.springbootdemo.genericserde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GenericDataTest {

	private ObjectMapper mapper = new ObjectMapper();

	private ObjectMapper mapperWithTypes = new ObjectMapper().enableDefaultTyping();

	@Test
	public void testString() throws JsonProcessingException {
		GenericData<?> strData = new GenericData<>(List.of("one", "two"));
		assertEquals("String", strData.inferType());

		String json = mapper.writeValueAsString(strData);
		assertEquals("{\"data\":[\"one\",\"two\"]}", json);
		GenericData<?> deser = mapper.readValue(json, GenericData.class);
		assertEquals(strData, deser);

		assertEquals("String", deser.inferType());
	}

	@Test
	public void testInteger() throws JsonProcessingException {
		GenericData<?> strData = new GenericData<>(List.of(1, 2));
		assertEquals("Integer", strData.inferType());

		String json = mapper.writeValueAsString(strData);
		assertEquals("{\"data\":[1,2]}", json);
		GenericData<?> deser = mapper.readValue(json, GenericData.class);
		assertEquals(strData, deser);

		assertEquals("Integer", deser.inferType());
	}


	@Test
	public void testWeird() throws JsonProcessingException {
		// Actual type on deserialization is Object, so it allows to mix types
		GenericData<?> deser = mapper.readValue("{\"data\":[1,\"two\"]}}", GenericData.class);
		assertEquals("Integer", deser.inferType());
		deser = mapper.readValue("{\"data\":[\"one\",2]}}", GenericData.class);
		assertEquals("String", deser.inferType());
	}
}