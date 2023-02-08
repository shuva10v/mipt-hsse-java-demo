package ru.mipt.hsse.course1.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.hsse.course1.jpa.model.Item;
import ru.mipt.hsse.course1.jpa.repository.ItemRepository;

@Service
public class ItemsManager {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RemoveValidatorService syncService;

	public void registerItem(String name) {
		if (!syncService.sync(name)) {
			throw new IllegalStateException("Validation failed");
		}
		Item item = new Item();
		item.setName(name);
		itemRepository.save(item);
	}
}
