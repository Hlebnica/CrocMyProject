package ru.croc;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
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
        boolean loop = true;
        while (loop) {
          // Название жанра
          String genreName;
          // Название фильма
          String movieTitle;
          // Число рейтинга
          int ratingNumber;
          // Название файла для импорта/экспорта
          String fileName;
          System.out.println("\nСписок действий:\n" +
              "0 - Выход из программы\n" +
              "1 - Получить список топа фильмов по жанру\n" +
              "2 - Оценить фильм по его названию\n" +
              "3 - Обновить оценку фильму\n" +
              "4 - Импорт новых жанров из csv файла\n" +
              "5 - Импорт новых фильмов из csv файла\n" +
              "6 - Экспорт жанров из БД в xml файл\n" +
              "7 - Экспорт фильмов из БД в xml файл\n" +
              "8 - Получить список рейтинга фильмов и их оценок от пользователей\n" +
              "9 - Получить список фильмов и их жанров\n");
          String action = input.nextLine();
          switch (action) {
            case "0":
              loop = false;
              break;
            case "1":
              System.out.println("Введите название жанра");
              genreName = input.nextLine();
              System.out.println("Введите количество выводимых фильмов");
              int limit = input.nextInt();
              input.nextLine();
              List<String> topComedies = movieDao.getTopMoviesByGenre(genreName.toLowerCase(),
                  limit);
              System.out.printf("Топ %d фильмов жанра %s:\n", limit, genreName);
              for (String movie : topComedies) {
                System.out.println(movie);
              }
              break;
            case "2":
              System.out.println("Введите название фильма, которому хотите поставить оценку");
              movieTitle = input.nextLine();
              System.out.println("Введите оценку от 0 до 10");
              ratingNumber = input.nextInt();
              input.nextLine();
              ratingDao.addRatingByUserNameAndTitle(userName, movieTitle, ratingNumber);
              System.out.println("Оценка добавлена");
              break;
            case "3":
              System.out.println("Введите название фильма, которому хотите обновить оценку");
              movieTitle = input.nextLine();
              System.out.println("Введите оценку от 0 до 10");
              ratingNumber = input.nextInt();
              input.nextLine();
              ratingDao.updateRatingByUserNameAndTitle(userName, movieTitle, ratingNumber);
              System.out.println("Оценка добавлена");
              break;
            case "4":
              System.out.println(
                  "Введите название файла для импорта новых жанров в виде fileName.csv");
              fileName = input.nextLine();
              GenreImporter.importGenresFromCSV(ConnectionConfig.CSV_FILE_PATH + fileName,
                  genreDao);
              System.out.println("Новые жанры успешно импортированы из " + fileName);
              break;
            case "5":
              System.out.println(
                  "Введите название файла для импорта новых фильмов в виде fileName.csv");
              fileName = input.nextLine();
              MovieImporter.importMoviesFromCSV(ConnectionConfig.CSV_FILE_PATH + fileName,
                  movieDao);
              System.out.println("Новые фильмы успешно импортированы из " + fileName);
              break;
            case "6":
              System.out.println(
                  "Введите название файла для экспорта жанров из БД в виде fileName.xml");
              fileName = input.nextLine();
              GenreExporter.exportGenresToXML(ConnectionConfig.XML_FILE_PATH + fileName,
                  genreDao);
              System.out.println("Жанры из БД успешно экспортированы в " + fileName);
              break;
            case "7":
              System.out.println(
                  "Введите название файла для экспорта фильмов из БД в виде fileName.xml");
              fileName = input.nextLine();
              MovieExporter.exportGenresToXML(ConnectionConfig.XML_FILE_PATH + fileName,
                  movieDao);
              System.out.println("Фильмы из БД успешно экспортированы в " + fileName);
              break;
            case "8":
              System.out.println("Список рейтинга фильмов и их оценок:");
              List<String> allRatingsWithUserAndMovieNames =
                  ratingDao.getAllRatingsWithUserAndMovieNames();
              for (String rating : allRatingsWithUserAndMovieNames) {
                System.out.println(rating);
              }
              break;
            case "9":
              System.out.println("Список фильмов и их жанров:");
              List<String> allMoviesWithGenreNames =
                  movieDao.getAllMoviesWithGenreNames();
              for (String movie : allMoviesWithGenreNames) {
                System.out.println(movie);
              }
              break;
            default:
              System.out.println("Неизвестная команда");
              break;
          }
        }
      } else {
        System.out.println(userName + " не найден в списке пользователей.");
      }

    } catch (SQLException e) {
      System.err.println("Ошибка при работе с БД: " + e.getMessage());
    } catch (JAXBException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}