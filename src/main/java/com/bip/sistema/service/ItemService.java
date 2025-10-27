package com.bip.sistema.service;

import com.bip.sistema.dao.ItemDAO;
import com.bip.sistema.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO(); // inicializa aqui
    }

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public List<Item> findAll() { return itemDAO.findAll(); }
    public Item findById(Long id) { return itemDAO.findByIdOrBarcode(id); }
    public void insert(Item item) { itemDAO.insert(item); }
    public void update(Item item) { itemDAO.update(item); }
    public void delete(Long id) { itemDAO.delete(id); }
}
