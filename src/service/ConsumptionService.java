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

    private List<Item> items;
    private List<User> users;
    private List<ConsumptionRecord> registers;

    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final ConsumptionRecordDAO consumptionRecordDAO;


    //Constructor
    public ConsumptionService(UserDAO userDAO, ItemDAO itemDAO, ConsumptionRecordDAO consumptionRecordDAO) {
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
        this.registers = new ArrayList<>();

        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.consumptionRecordDAO = consumptionRecordDAO;

    }

    // methods
    public void registerItem(Item item){
        items.add(item);
    }

    public void registerUser(User user){
        users.add(user);
    }

    public void recordConsumption(User user, Item item, int quantity) {
        item.consume(quantity);
        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        registers.add(record);
    }

    public void recordConsumption(String userId, String itemId, int quantity) {
        User user = userDAO.findById(userId);
        System.out.println(user.getName());

        Item item = itemDAO.findById(itemId);
        System.out.println(item.getName());

        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        consumptionRecordDAO.insert(record);
    }

    public void recordConsumption(User user, Item item) {
        item.consume();
        ConsumptionRecord record = new ConsumptionRecord(user, item, LocalDateTime.now());
        registers.add(record);
    }

    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Item findItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }



    public void showConsumptionReport(){
        if(!users.isEmpty()){
            System.out.println("--- Users ---");
            for (User u : users) {
                u.displayInfo();
            }
            System.out.println();
        }

        if(!items.isEmpty()){
            System.out.println("--- Itens ---");
            for (Item i : items) {
                i.displayInfo();
            }
            System.out.println();
        }

        if(!registers.isEmpty()){
            System.out.println("--- registros ---");
            for (ConsumptionRecord r : registers) {
                r.displayInfo();
            }
            System.out.println();
        }

    }


}
