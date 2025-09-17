import java.util.Scanner;

import model.Employee;
import model.Item;
import model.User;
import service.ConsumptionService;

public class Main {
    public static void main(String[] args) {
        ConsumptionService sistema = new ConsumptionService();

        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("Sistema de cadastro de consumo");
            System.out.println("1 - Cadastro de consumo");
            System.out.println("2 - Cadastro de material");
            System.out.println("3 - Cadastro de funcionario");
            System.out.println("4 - Relatório");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o id do funcionario (ex: U001): ");
                    String idUser = scanner.nextLine();

                    User user = sistema.findUserById(idUser);

                    if (user == null) {
                        System.out.println("Funcionário não encontrado.");
                        continue;
                    }

                    System.out.print("Digite o id do material (ex: I001): ");
                    String idItem = scanner.nextLine();

                    Item item = sistema.findItemById(idItem);

                    if (item == null) {
                        System.out.println("Item não encontrado.");
                        continue;
                    }

                    System.out.print("Digite a quantidade a ser consumida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    sistema.recordConsumption(user, item, quantity);
                }
                case 2 -> {
                    System.out.print("Digite o nome do material: ");
                    String name = scanner.nextLine();

                    System.out.print("Digite a categoria do material: ");
                    String category = scanner.nextLine();

                    System.out.print("Digite a quantidade atual do material: ");
                    int currentQuantity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite a quantidade mínima do material: ");
                    int minimumQuantity = scanner.nextInt();
                    scanner.nextLine();

                    Item newItem = new Item(name, category, currentQuantity, minimumQuantity);
                    sistema.registerItem(newItem);
                }
                case 3 -> {
                    System.out.print("Digite o nome do funcionário: ");
                    String name = scanner.nextLine();
                    Employee newUser = new Employee(name);
                    sistema.registerUser(newUser);

                }
                case 4 -> {
                    sistema.showConsumptionReport();
                }
            }

        } while (opcao != 0);
    }
}
