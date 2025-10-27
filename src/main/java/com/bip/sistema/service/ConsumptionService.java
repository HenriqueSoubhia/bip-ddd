package com.bip.sistema.service;

import com.bip.sistema.dao.ConsumptionRecordDAO;
import com.bip.sistema.dao.ItemDAO;
import com.bip.sistema.dao.UserDAO;
import com.bip.sistema.model.ConsumptionRecord;
import com.bip.sistema.model.Item;
import com.bip.sistema.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionService {

    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final ConsumptionRecordDAO consumptionRecordDAO;

    public ConsumptionService(UserDAO userDAO, ItemDAO itemDAO, ConsumptionRecordDAO consumptionRecordDAO) {
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.consumptionRecordDAO = consumptionRecordDAO;
    }

    // Registrar consumo
    public ConsumptionRecord recordConsumption(Long userId, Long itemId, int quantity) {
        User user = userDAO.findByIdOrBadge(userId);
        Item item = itemDAO.findByIdOrBarcode(itemId);

        if (user == null) throw new IllegalArgumentException("Usuário não encontrado");
        if (item == null) throw new IllegalArgumentException("Item não encontrado");

        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        consumptionRecordDAO.insert(record);
        return record; // retornar objeto criado
    }

    public ConsumptionRecord recordConsumption(Long userId, Long itemId) {
        return recordConsumption(userId, itemId, 1);
    }

    // Listar todos os registros de consumo
    public List<ConsumptionRecord> findAll() {
        return consumptionRecordDAO.findAll();
    }

    // Buscar consumo por id (opcional)
    public ConsumptionRecord findById(Long id) {
        return consumptionRecordDAO.findById(id);
    }
}
