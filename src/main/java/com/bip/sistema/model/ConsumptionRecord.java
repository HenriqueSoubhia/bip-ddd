package com.bip.sistema.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumption_records")
public class ConsumptionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int quantity;

    public ConsumptionRecord() {
    }

    public ConsumptionRecord(User user, Item item, int quantity, LocalDateTime date) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
        this.date = date;
    }

    public ConsumptionRecord(User user, Item item, LocalDateTime date) {
        this(user, item, 1, date);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    // Método de exibição (opcional)
    public void displayInfo() {
        System.out.printf(
                "ID: %d, Item: %s, User: %s, Quantity: %d, Date: %s%n",
                id,
                item != null ? item.getName() : "N/A",
                user != null ? user.getName() : "N/A",
                quantity,
                date
        );
    }
}
