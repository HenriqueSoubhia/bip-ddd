import java.awt.*;
import java.util.Scanner;

import dao.ConsumptionRecordDAO;
import dao.ItemDAO;
import dao.UserDAO;
import model.Employee;
import model.Item;
import model.User;
import service.ConsumptionService;

public class Main {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        ConsumptionRecordDAO consumptionRecordDAO = new ConsumptionRecordDAO();



        ConsumptionService sistema = new ConsumptionService(userDAO, itemDAO, consumptionRecordDAO);

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
                    System.out.print("Digite o crachá do funcionario: ");
                    int idUser = scanner.nextInt();

                    System.out.print("Digite o codigo de barra do material: ");
                    int idItem = scanner.nextInt();

                    System.out.print("Digite a quantidade a ser consumida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    sistema.recordConsumption(idUser, idItem, quantity);
                }
                case 2 -> {
                    System.out.print("Digite o nome do material: ");
                    String name = scanner.nextLine();

                    System.out.print("Digite a categoria do material: ");
                    String category = scanner.nextLine();

                    System.out.print("Digite o código de barras do material: ");
                    String barcode = scanner.nextLine();
                    Long code = null;
                    if (!barcode.isBlank()) {
                        code = Long.parseLong(barcode); // usa Long para números grandes
                    }

                    System.out.print("Digite a quantidade atual do material: ");
                    int currentQuantity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite a quantidade mínima do material: ");
                    int minimumQuantity = scanner.nextInt();
                    scanner.nextLine();

                    Item newItem = new Item(name, category, code, currentQuantity, minimumQuantity);

                    ItemDAO dao = new ItemDAO();
                    dao.insert(newItem);

                    System.out.println("Item cadastrado com sucesso!");

                }
                case 3 -> {
                    System.out.print("Digite o nome do funcionário: ");
                    String name = scanner.nextLine();

                    System.out.print("Digite o código do crachá (ou ENTER para pular): ");
                    String badgeInput = scanner.nextLine();
                    Long badge = null;
                    if (!badgeInput.isBlank()) {
                        badge = Long.parseLong(badgeInput); // usa Long para números grandes
                    }

                    Employee newUser = new Employee(name, badge);

                    UserDAO dao = new UserDAO();
                    dao.insert(newUser);

                    System.out.println("Funcionário cadastrado com sucesso! ID: " + newUser.getId());


                }
                case 4 -> {
//                    sistema.showConsumptionReport();
                }
            }

        } while (opcao != 0);
    }
}
