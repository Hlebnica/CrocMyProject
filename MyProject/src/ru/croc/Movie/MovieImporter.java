package ru.croc.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class MovieImporter {

  public static void importMoviesFromCSV(String filePath, MovieDao movieDao)
      throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] fields = line.split(",");
      String title = fields[0];
      int genreId = Integer.parseInt(fields[1]);
      Movie movie = new Movie(title, genreId);
      movieDao.addMovie(movie);
    }
    reader.close();
  }
}
