package model;

import java.time.LocalDateTime;

public class ConsumptionRecord {

    private int id;
    private Item item;
    private User user;
    private LocalDateTime date;
    private int quantity;
    private int userId;
    private int itemId;



    // Constructors
    public ConsumptionRecord(User user, Item item, int quantity, LocalDateTime date){
        this.item = item;
        this.user = user;
        this.quantity = quantity;
        this.date = date;
    }

    public ConsumptionRecord(User user, Item item, LocalDateTime date) {
        this(user, item, 1, date);
    }

    public ConsumptionRecord() {

    }

    public ConsumptionRecord(int id, User user, Item item, int quantity, LocalDateTime date){
        this.id = id;
        this.item = item;
        this.user = user;
        this.quantity = quantity;
        this.date = date;
    }

    //mehtods

    public void displayInfo(){
        if (item != null && user != null) {
            System.out.printf("ID: %d, Item: %s, User: %s, Quantity: %d, Date: %s%n",
                    this.id, item.getName(), user.getName(), quantity, date);
        } else {
            System.out.printf("ID: %d, Item ou User não carregado, Quantity: %d, Date: %s%n",
                    this.id, quantity, date);
        }
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
