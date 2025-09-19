package dao;

import model.Employee;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CREATE
    public void insert(User user) {
        String sql = "INSERT INTO users (name, badge_code) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            if (user.getBadgeCode() != null) {
                stmt.setLong(2, user.getBadgeCode());
            } else {
                stmt.setNull(2, java.sql.Types.BIGINT);
            }

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }

    // READ
    public User findByIdOrBadge(Long param) {
        String sql = "SELECT * FROM users WHERE id = ? OR badge_code = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, param);
            stmt.setLong(2, param);

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

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new Employee(
                        rs.getString("name"),
                        rs.getLong("badge_code")
                );
                user.setId(rs.getInt("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }

        return users;
    }

    // UPDATE
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, badge_code = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            if (user.getBadgeCode() != null) {
                stmt.setLong(2, user.getBadgeCode());
            } else {
                stmt.setNull(2, java.sql.Types.BIGINT);
            }
            stmt.setInt(3, user.getId());

            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário removido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário: " + e.getMessage(), e);
        }
    }
}
