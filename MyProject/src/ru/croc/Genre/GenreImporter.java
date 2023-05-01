package ru.croc.Genre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import ru.croc.Genre.Genre;

public class GenreImporter {

  /** Импорт новых жанров из CSV файла
   *
   * @param filePath - название файла
   * @param genreDao - DAO жанров
   * @throws IOException
   * @throws SQLException
   */
  public static void importGenresFromCSV(String filePath, GenreDao genreDao)
      throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] fields = line.split(",");
      String genre_name = fields[0];
      Genre genre = new Genre(0, genre_name);
      genreDao.addGenre(genre);
    }
    reader.close();
  }
}
