import Genre.*;
import Movie.*;
import Rating.*;
import Users.*;

import java.io.IOException;
import java.util.*;
import java.sql.*;

public class Main {

  public static void main(String[] args) {
    try (Connection connection = DriverManager.getConnection(
        ConnectionConfig.URL, ConnectionConfig.LOGIN, ConnectionConfig.PASSWORD);) {
      GenreDao genreDao = new GenreDao(connection);
      MovieDao movieDao = new MovieDao(connection);
      UsersDao usersDao = new UsersDao(connection);
      RatingDao ratingDao = new RatingDao(connection);

//      MovieImporter.importMoviesFromCSV(ConnectionConfig.CSV_FILE_PATH + "newMovies.csv", movieDao);
//      GenreImporter.importGenresFromCSV(ConnectionConfig.CSV_FILE_PATH + "newGenres.csv", genreDao);

//      List<Genre> genres = genreDao.getAllGenres();
//      for (Genre genre : genres) {
//        System.out.println(genre.getId() + " " + genre.getGenre_name());
//      }

//      List<Movie> movies = movieDao.getAllMovies();
//      for (Movie movie : movies) {
//        System.out.println(movie.getId() + " " + movie.getTitle());
//      }

      System.out.println("Done");
    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    }
  }
}