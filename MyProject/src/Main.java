import Genre.Genre;
import Genre.GenreDao;
import Movie.Movie;
import Movie.MovieDao;
import Rating.Rating;
import Rating.RatingDao;
import Users.Users;
import Users.UsersDao;
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

//      List<Genre> genres = genreDao.getAllGenres();
//      for (Genre genre : genres) {
//        System.out.println(genre.getId() + " " + genre.getGenre_name());
//      }

      List<Movie> movies = movieDao.getAllMovies();
      for (Movie movie : movies) {
        System.out.println(movie.getId() + " " + movie.getTitle());
      }




    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    }
  }
}