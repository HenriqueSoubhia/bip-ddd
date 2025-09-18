
import dao.ConnectionFactory;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
