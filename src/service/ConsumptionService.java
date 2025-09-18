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


    public void recordConsumption(int userId, int itemId, int quantity) {
        User user = userDAO.findByIdOrBadge(userId);
        Item item = itemDAO.findById(itemId);

        if (user == null) throw new IllegalArgumentException("Usuário não encontrado");
        if (item == null) throw new IllegalArgumentException("Item não encontrado");

        // consome estoque
        item.consume(quantity);

        // atualiza quantidade no banco
        // itemDAO.update(item);

        // cria registro de consumo
        ConsumptionRecord record = new ConsumptionRecord(user, item, quantity, LocalDateTime.now());
        consumptionRecordDAO.insert(record);
    }

    public void recordConsumption(int userId, int itemId) {
        recordConsumption(userId, itemId, 1);
    }



//    public void showConsumptionReport(){
//        if(!users.isEmpty()){
//            System.out.println("--- Users ---");
//            for (User u : users) {
//                u.displayInfo();
//            }
//            System.out.println();
//        }
//
//        if(!items.isEmpty()){
//            System.out.println("--- Itens ---");
//            for (Item i : items) {
//                i.displayInfo();
//            }
//            System.out.println();
//        }
//
//        if(!registers.isEmpty()){
//            System.out.println("--- registros ---");
//            for (ConsumptionRecord r : registers) {
//                r.displayInfo();
//            }
//            System.out.println();
//        }
//
//    }


}
