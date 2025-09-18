package dao;

import model.ConsumptionRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConsumptionRecordDAO {

    public void insert(ConsumptionRecord record) {
        String sql = "INSERT INTO consumption_records (ID, ITEM_ID, USER_ID, CONSUMPTION_DATE, QUANTITY) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, record.getId()); // ID do registro (C001, C002...)
            stmt.setInt(2, record.getItem().getId());
            stmt.setInt(3, record.getUser().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(record.getDate())); // LocalDateTime -> Timestamp
            stmt.setInt(5, record.getQuantity());

            stmt.executeUpdate();
            System.out.println("Registro de consumo inserido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir registro de consumo: " + e.getMessage(), e);
        }
    }
}
