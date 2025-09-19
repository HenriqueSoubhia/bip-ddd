package dao;

import model.Employee;
import model.Item;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Item findByIdOrBarcode(Long param) {
        String sql = "SELECT * FROM items WHERE id = ? OR barcode = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, param);
            stmt.setLong(2, param);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getLong("barcode"),
                        rs.getInt("current_quantity"),
                        rs.getInt("minimum_quantity")
                );
                item.setId(rs.getInt("id")); // importante para usar depois em consumo
                return item;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar item: " + e.getMessage(), e);
        }
    }


    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        List<Item> items = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getLong("barcode"),
                        rs.getInt("current_quantity"),
                        rs.getInt("minimum_quantity")
                );
                item.setId(rs.getInt("id"));
                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens: " + e.getMessage(), e);
        }

        return items;
    }

    public void update(Item item) {
        String sql = "UPDATE items SET name=?, category=?, barcode=?, current_quantity=?, minimum_quantity=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getCategory());
            if (item.getBarcode() != null) {
                stmt.setLong(3, item.getBarcode());
            } else {
                stmt.setNull(3, java.sql.Types.BIGINT);
            }
            stmt.setInt(4, item.getCurrentQuantity());
            stmt.setInt(5, item.getMinimumQuantity());
            stmt.setInt(6, item.getId());

            stmt.executeUpdate();
            System.out.println("Item atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM items WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Item removido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover item: " + e.getMessage(), e);
        }
    }


}
