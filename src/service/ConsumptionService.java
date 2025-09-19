package service;

import dao.ConsumptionRecordDAO;
import dao.ItemDAO;
import dao.UserDAO;
import model.ConsumptionRecord;
import model.Item;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionService {


    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final ConsumptionRecordDAO consumptionRecordDAO;


    //Constructor
    public ConsumptionService(UserDAO userDAO, ItemDAO itemDAO, ConsumptionRecordDAO consumptionRecordDAO) {

        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.consumptionRecordDAO = consumptionRecordDAO;

    }

    // methods


    public void recordConsumption(Long userId, Long itemId, int quantity) {
        User user = userDAO.findByIdOrBadge(userId);
        Item item = itemDAO.findByIdOrBarcode(itemId);

        if (user == null) throw new IllegalArgumentException("Usuário não encontrado");
        if (item == null) throw new IllegalArgumentException("Item não encontrado");

        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        consumptionRecordDAO.insert(record);
    }

    public void recordConsumption(Long userId, Long itemId) {
        recordConsumption(userId, itemId, 1);
    }

    public void showAllData() {
        System.out.println("===== Usuários =====");
        List<User> users = userDAO.findAll();
        for (User u : users) {
            u.displayInfo();
        }

        System.out.println("\n===== Itens =====");
        List<Item> items = itemDAO.findAll();
        for (Item i : items) {
            i.displayInfo();
        }

        System.out.println("\n===== Registros de Consumo =====");
        List<ConsumptionRecord> records = consumptionRecordDAO.findAll();
        for (ConsumptionRecord r : records) {
            r.displayInfo();
        }

        System.out.println("\n");

    }





}
