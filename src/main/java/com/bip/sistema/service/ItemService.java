package com.bip.sistema.service;

import com.bip.sistema.model.Item;
import com.bip.sistema.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // CREATE
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // READ
    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public Item findById(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item findByBarcode(Long barcode) {
        return itemRepository.findByBarcode(barcode);
    }

    // UPDATE
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    // DELETE
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }
}
