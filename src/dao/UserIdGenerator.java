package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdGenerator {

    public static int getNextId() {
        String sql = "SELECT MAX(ID) AS MAX_ID FROM users";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String maxId = rs.getString("MAX_ID"); // exemplo: "U012"
                if (maxId != null) {
                    // remove a letra U e converte para int
                    int numericPart = Integer.parseInt(maxId.substring(1));
                    return numericPart + 1;
                }
            }
            return 1; // tabela vazia, começa do 1

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar próximo ID: " + e.getMessage(), e);
        }
    }
}
