package ru.croc;

import jakarta.xml.bind.JAXBException;
import ru.croc.Genre.*;
import ru.croc.Movie.*;
import ru.croc.Rating.*;
import ru.croc.Users.*;

import java.util.*;
import java.sql.*;

public class Main {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    try (Connection connection = DriverManager.getConnection(
        ConnectionConfig.URL, ConnectionConfig.LOGIN, ConnectionConfig.PASSWORD);) {
      GenreDao genreDao = new GenreDao(connection);
      MovieDao movieDao = new MovieDao(connection);
      UsersDao usersDao = new UsersDao(connection);
      RatingDao ratingDao = new RatingDao(connection);

      // Вход пользователя
      System.out.println("Введите имя пользователя");
      String userName = input.nextLine();
      List<Users> users = usersDao.getAllUsers();

      boolean isUserExists = users.stream()
          .map(Users::getUserName)
          .anyMatch(userName::equals);

      if (isUserExists) {
        System.out.printf("Текущий пользователь в системе: %s \n", userName);
        System.out.println("Список действий\n" +
            "1 - Получить список фильмов по жанру\n" +
            "2 - Оценить фильм по его названию\n" +
            "3 - Обновить оценку фильму\n");
        String action = input.nextLine();
        String genreName;
        String movieTitle;
        int ratingNumber;
        switch (action) {
          case "1":
            System.out.println("Введите название жанра");
            genreName = input.nextLine();
            System.out.println("Введите количество выводимых фильмов");
            int limit = input.nextInt();
            List<String> topComedies = movieDao.getTopMoviesByGenre(genreName.toLowerCase(), limit);
            System.out.printf("Топ %d фильмов жанра %s:\n", limit, genreName);
            for (String movie : topComedies) {
              System.out.println(movie);
            }
            break;
          case "2":
            System.out.println("Введите название фильма");
            movieTitle = input.nextLine();
            System.out.println("Введите оценку от 0 до 10");
            ratingNumber = input.nextInt();
            ratingDao.addRatingByUserNameAndTitle(userName, movieTitle, ratingNumber);
            System.out.println("Оценка добавлена");
            break;

        }


      } else {
        System.out.println(userName + " не найден в списке пользователей.");
      }

//      String userName = input.nextLine();
//
//      List<Users> users = usersDao.getAllUsers();
//
//      for (Users user : users) {
//        System.out.println(user.getUserName());
//      }

//      GenreImporter.importGenresFromCSV(ConnectionConfig.CSV_FILE_PATH + "newGenres.csv", genreDao);
//      MovieImporter.importMoviesFromCSV(ConnectionConfig.CSV_FILE_PATH + "newMovies.csv", movieDao);

//        ratingDao.updateRatingByUserNameAndTitle("User2", "Абоба", 6);

      GenreExporter.exportGenresToXML(ConnectionConfig.XML_FILE_PATH + "geners.xml", genreDao);
      MovieExporter.exportGenresToXML(ConnectionConfig.XML_FILE_PATH + "movies.xml", movieDao);



//      List<Rating> ratings = ratingDao.getAllRatings();
//      for (Rating rating : ratings) {
//        System.out.println(rating.getId() + " " + rating.getUserId() + " " + rating.getMovieId() + " " + rating.getRatingDigit());
//      }

//      List<Genre> genres = genreDao.getAllGenres();
//      for (Genre genre : genres) {
//        System.out.println(genre.getId() + " " + genre.getGenre_name());
//      }

//      List<Movie> movies = movieDao.getAllMovies();
//      for (Movie movie : movies) {
//        System.out.println(movie.getId() + " " + movie.getTitle());
//      }

    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}