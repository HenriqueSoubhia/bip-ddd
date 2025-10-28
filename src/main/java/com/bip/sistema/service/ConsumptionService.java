package com.bip.sistema.service;

import com.bip.sistema.model.ConsumptionRecord;
import com.bip.sistema.model.User;
import com.bip.sistema.model.Item;
import com.bip.sistema.repository.ConsumptionRepository;
import com.bip.sistema.repository.UserRepository;
import com.bip.sistema.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionService {

    private final ConsumptionRepository consumptionRecordRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ConsumptionService(
            ConsumptionRepository consumptionRecordRepository,
            UserRepository userRepository,
            ItemRepository itemRepository
    ) {
        this.consumptionRecordRepository = consumptionRecordRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public ConsumptionRecord recordConsumption(Long userId, Long itemId, int quantity) {
        User user = userRepository.findByBadgeCode(userId);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + userId);
        }

        Item item = itemRepository.findById(itemId.intValue()).orElseThrow(() ->
                new IllegalArgumentException("Item não encontrado com ID: " + itemId)
        );
        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        return consumptionRecordRepository.save(record);
    }

    public ConsumptionRecord recordConsumption(Long userId, Long itemId) {
        return recordConsumption(userId, itemId, 1);
    }

    public List<ConsumptionRecord> listAll() {
        return consumptionRecordRepository.findAll();
    }

    public void deleteRecord(Long id) {
        consumptionRecordRepository.deleteById(id);
    }

    public ConsumptionRecord findById(Long id) {
        return consumptionRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro de consumo não encontrado com ID: " + id));
    }
}
