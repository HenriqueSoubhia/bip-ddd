public abstract class User {
    private String name;
    private String id;

    private static int counter = 1;

    public User(String name) {
        this.name = name;
        this.id = generateId();
    }

    private String generateId() {
        String newId = String.format("U%03d", counter);
        counter++;
        return newId;
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
