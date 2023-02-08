package ru.mipt.hsse.course1.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemsManagerTest {
	@Autowired
	private ItemsManager itemsManager;

	@Autowired
	private ItemRepository itemRepository;

	@MockBean
	private RemoveValidatorService validatorService;


	@Test
	public void testSuccessfulCreate() {
		when(validatorService.sync(anyString())).thenReturn(true);
		itemsManager.registerItem("pizza");
		var items =  itemRepository.findAll();
		assertThat(items, hasItem(hasProperty("name", equalTo("pizza"))));
		verify(validatorService, times(1)).sync(anyString());
	}

	@Test
	public void testInvalidName() {
		when(validatorService.sync(anyString())).thenReturn(false);
		assertThrows(IllegalStateException.class, () -> itemsManager.registerItem("prohibited"));
		verify(validatorService, times(1)).sync(anyString());
	}

}