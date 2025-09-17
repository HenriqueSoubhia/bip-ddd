public class Item {
    private String id;
    private String name;
    private int currentQuantity;
    private int minimumQuantity;
    private String category;

    private static int counter = 1;

    // Constructors
    public Item(String name) {
        this.id = generateId();
        this.name = name;
        this.category = "Uncategorized";
        this.currentQuantity = 0;
        this.minimumQuantity = 0;
    }

    public Item(String name, String category) {
        this.id = generateId();
        this.name = name;
        this.category = category;
        this.currentQuantity = 0;
        this.minimumQuantity = 0;
    }

    public Item(String name, int currentQuantity, int minimumQuantity) {
        this.id = generateId();
        this.name = name;
        this.category = "Uncategorized";
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    public Item(String name, String category, int currentQuantity, int minimumQuantity) {
        this.id = generateId();
        this.name = name;
        this.category = category;
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    // methods
    private String generateId() {
        String newId = String.format("I%03d", counter++);
        return newId;
    }

    public void consume(){
        int quantity = 1;
        if (quantity > currentQuantity) {
            throw new IllegalArgumentException("Insufficient stock to consume " + quantity + " units.");
        }
        currentQuantity -= quantity;
    }

    public void consume(int quantity){
        if (quantity > currentQuantity) {
            throw new IllegalArgumentException("Insufficient stock to consume " + quantity + " units.");
        }
        currentQuantity -= quantity;
    }

    public boolean isBelowMinimum(){
        return currentQuantity < minimumQuantity;
    }

    public void displayInfo(){
        System.out.printf("Name: %s, Category: %s, Quantity: %s, ID: %s%n", name, category, currentQuantity,id);
    }


    // getters and setters
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    private void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    private void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    public static int getCounter() {
        return counter;
    }

    private static void setCounter(int counter) {
        Item.counter = counter;
    }
}
