package com.bip.sistema.repository;

import com.bip.sistema.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByBarcode(Long barcode);
}
