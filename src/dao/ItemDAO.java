package dao;

import model.Employee;
import model.Item;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {

    public void insert(Item item) {
        String sql = "INSERT INTO items (BARCODE, NAME, CATEGORY, CURRENT_QUANTITY, MINIMUM_QUANTITY) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, item.getBarcode());
            stmt.setString(2, item.getName());
            stmt.setString(3, item.getCategory());
            stmt.setInt(4, item.getCurrentQuantity());
            stmt.setInt(5, item.getMinimumQuantity());

            stmt.executeUpdate();
            System.out.println("Item inserido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item: " + e.getMessage(), e);
        }
    }

    public Item findById(int id) {
        String sql = "SELECT * FROM items WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("NAME");
                String category = rs.getString("CATEGORY");
                int currentQuantity = rs.getInt("CURRENT_QUANTITY");
                int minimumQuantity = rs.getInt("MINIMUM_QUANTITY");

                return new Item(name,category, currentQuantity, minimumQuantity);
            } else {
                return null; // não encontrou o item
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar item: " + e.getMessage(), e);
        }
    }

}
