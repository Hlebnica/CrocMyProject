import Genre.GenreDao;
import Movie.MovieDao;
import Rating.RatingDao;
import Users.UsersDao;
import java.sql.*;

public class Main {

  public static void main(String[] args) {
    try (Connection connection = DriverManager.getConnection(
        ConnectionConfig.URL, ConnectionConfig.LOGIN, ConnectionConfig.PASSWORD);) {
      GenreDao genreDao = new GenreDao(connection);
      MovieDao movieDao = new MovieDao(connection);
      UsersDao usersDao = new UsersDao(connection);
      RatingDao ratingDao = new RatingDao(connection);




    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    }
  }
}