package model;

public class Employee extends User {

    public Employee(String nome) {
        super(nome);
    }

    public Employee(String nome, Long badgeCode) {
        super(nome, badgeCode);
    }

}
