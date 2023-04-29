import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/** Путь до места хранения БД, логином и паролем для удобства
 *
 */
public class ConnectionConfig {
  public static final String URL = "jdbc:h2:tcp://localhost:9092/E:/course3sem6/CrocMyProject/MyProject/db/movies";
  public static final String LOGIN = "admin";
  public static final String PASSWORD = "admin";
}
