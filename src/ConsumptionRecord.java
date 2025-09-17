import java.time.LocalDateTime;

public class ConsumptionRecord {

    private String id;
    private Item item;
    private User user;
    private LocalDateTime date;
    private int quantity;

    private static int counter = 1;


    // Constructors
    public ConsumptionRecord(User user, Item item, int quantity, LocalDateTime date){
        this.id = generateId();
        this.item = item;
        this.user = user;
        this.quantity = quantity;
        this.date = date;
    }

    public ConsumptionRecord(User user, Item item, LocalDateTime date) {
        this(user, item, 1, date);
    }

    //mehtods
    private String generateId() {
        return String.format("C%03d", counter++);
    }

    public void displayInfo(){
        System.out.printf("Item: %s, User: %s, Quantity: %d, Date: %s%n",
            item.getName(), user.getName(), quantity, date);

    }

    //getters and setters
    public String getId() {
        return id;
    }

    private void setId(String id) {
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

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
