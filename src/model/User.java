package model;

public class User {
    private int id;           // ID gerado pelo banco
    private String name;
    private Long badgeCode; // opcional (código do crachá)

    // Construtores
    public User(String name, Long badgeCode) {
        this.name = name;
        this.badgeCode = badgeCode;
    }

    public User(String name) {
        this(name, null);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {  // DAO vai setar depois de salvar
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBadgeCode() {
        return badgeCode;
    }

    public void setBadgeCode(Long badgeCode) {
        this.badgeCode = badgeCode;
    }

    public void displayInfo() {
        System.out.printf(
                "ID: %d, Name: %s, Badge: %s%n",
                id,
                name,
                (badgeCode != null ? badgeCode : "N/A")
        );
    }
}
