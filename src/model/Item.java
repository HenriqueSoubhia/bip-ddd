package model;

public class Item {
    private int id;              // gerado pelo banco (IDENTITY)
    private Long barcode;      // opcional (código de barras ou crachá)
    private String name;
    private String category;
    private int currentQuantity;
    private int minimumQuantity;

    // Construtores
    public Item(String name, String category, Long barcode, int currentQuantity, int minimumQuantity) {
        this.name = name;
        this.category = category;
        this.barcode = barcode;
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    public Item(String name, String category, int currentQuantity, int minimumQuantity) {
        this(name, category, null, currentQuantity, minimumQuantity);
    }

    public Item(String name, int currentQuantity, int minimumQuantity) {
        this(name, "Uncategorized", currentQuantity, minimumQuantity);
    }

    public Item(String name, String category) {
        this(name, category, 0, 0);
    }

    public Item(String name) {
        this(name, "Uncategorized", 0, 0);
    }

    // Métodos de negócio
    public void consume() {
        consume(1);
    }

    public void consume(int quantity) {
        if (quantity > currentQuantity) {
            throw new IllegalArgumentException("Estoque insuficiente para consumir " + quantity + " unidades.");
        }
        currentQuantity -= quantity;
    }

    public boolean isBelowMinimum() {
        return currentQuantity < minimumQuantity;
    }

    public void displayInfo() {
        System.out.printf(
                "ID: %d, Barcode: %s, Name: %s, Category: %s, Quantity: %d, Min: %d%n",
                id,
                (barcode != null ? barcode : "N/A"),
                name,
                category,
                currentQuantity,
                minimumQuantity
        );
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {  // o DAO vai setar depois de salvar no banco
        this.id = id;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }
}
