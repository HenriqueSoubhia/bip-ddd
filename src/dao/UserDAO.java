package dao;

import model.Employee;
import model.User;
import java.sql.*;

public class UserDAO {

    public void insert(User user) {
        String sql = "INSERT INTO users (name, badge_code) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBadgeCode());

            stmt.executeUpdate();


            System.out.println("Usuário inserido com sucesso! ID gerado: " + user.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }


    public User findByIdOrBadge(int param) {
        String sql = "SELECT * FROM users WHERE TO_CHAR(id) = ? OR badge_code = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, param);
            stmt.setInt(2, param);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new Employee(
                        rs.getString("name"),
                        rs.getLong("badge_code")
                );
                user.setId(rs.getInt("id"));
                return user;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar usuário: " + e.getMessage(), e);
        }
    }


}
