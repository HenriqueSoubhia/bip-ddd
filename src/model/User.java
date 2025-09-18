package model;

import dao.UserIdGenerator;

public abstract class User {
    private String name;
    private String id;

    public User(String name) {
        this.name = name;
        this.id = generateId();
    }

    private String generateId() {
        int nextNumber = UserIdGenerator.getNextId();
        return String.format("U%03d", nextNumber);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setId(String id) {
        this.id = id;
    }

    public void displayInfo(){
        System.out.printf("Nome: %s, ID: %s%n", name, id);
    }

}
