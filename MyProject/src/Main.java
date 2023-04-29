import java.sql.*;


public class Main {

  public static void main(String[] args) {
    String url = "jdbc:h2:tcp://localhost:9092/E:/course3sem6/CrocMyProject/MyProject/db/movies";

    try (Connection conn = DriverManager.getConnection(url, "admin", "admin")) {
        System.out.println("Установили соединение с БД");
    } catch (SQLException e) {
        System.err.println("Ошибка при работе с БД: " + e.getMessage());
      }
  }
}