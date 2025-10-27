package com.bip.sistema.dao;

import com.bip.sistema.model.ConsumptionRecord;
import com.bip.sistema.model.Item;
import com.bip.sistema.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsumptionRecordDAO {

    public void insert(ConsumptionRecord record) {
        String sql = "INSERT INTO consumption_records (user_id, item_id, quantity, consumption_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, record.getUser().getId());
            stmt.setInt(2, record.getItem().getId());
            stmt.setInt(3, record.getQuantity());
            stmt.setTimestamp(4, Timestamp.valueOf(record.getDate()));

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir registro de consumo: " + e.getMessage(), e);
        }
    }

    public List<ConsumptionRecord> findAll() {
        String sql = "SELECT * FROM consumption_records";
        List<ConsumptionRecord> records = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                LocalDateTime date = rs.getTimestamp("consumption_date").toLocalDateTime();

                // Reconstruir User e Item completos usando os DAOs
                User user = new UserDAO().findByIdOrBadge((long) userId); // ou findById se tiver
                Item item = new ItemDAO().findByIdOrBarcode((long) itemId); // ou findById se tiver

                if (user != null && item != null) {
                    ConsumptionRecord record = new ConsumptionRecord(
                            rs.getLong("id"),  // ID do banco
                            user,
                            item,
                            quantity,
                            date
                    );
                    records.add(record);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros de consumo: " + e.getMessage(), e);
        }

        return records;
    }


    public void delete(int id) {
        String sql = "DELETE FROM consumption_records WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Registro de consumo removido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover registro de consumo: " + e.getMessage(), e);
        }
    }

    public ConsumptionRecord findById(Long id) {
        String sql = "SELECT * FROM consumption_records WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                LocalDateTime date = rs.getTimestamp("consumption_date").toLocalDateTime();

                // Reconstruir User e Item usando os DAOs
                User user = new UserDAO().findByIdOrBadge((long) userId);
                Item item = new ItemDAO().findByIdOrBarcode((long) itemId);

                if (user != null && item != null) {
                    return new ConsumptionRecord(
                            rs.getLong("id"), // ID do banco
                            user,
                            item,
                            quantity,
                            date
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar registro de consumo: " + e.getMessage(), e);
        }

        return null; // retorna null se não encontrar
    }


}
