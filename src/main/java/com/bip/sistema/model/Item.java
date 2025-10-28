package com.bip.sistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long barcode;

    private String name;

    private String category;

    @Column(name = "current_quantity")
    private int currentQuantity;

    @Column(name = "minimum_quantity")
    private int minimumQuantity;

    // Construtores
    public Item() {

    }

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
