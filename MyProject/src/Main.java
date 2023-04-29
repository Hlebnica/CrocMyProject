import java.sql.*;

public class Main {

  public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(ConnectionConfig.URL, "admin", "admin")) {
      String sql = "select * from users";
      try (Statement statement = conn.createStatement()) {
        boolean hasResult = statement.execute(sql);
        if (hasResult) {
          try (ResultSet resultSet = statement.getResultSet()) {
            while (resultSet.next()) {
              String username = resultSet.getString(2);
              String format = String.format("%s", username);
              System.out.println(format);
            }
          }
        }
      }
    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    }
  }
}