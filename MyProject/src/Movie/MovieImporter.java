package Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


/*
* TODO Разобраться с id и проверить работу метода
* */
public class MovieImporter {
  public static void importMoviesFromCSV(String filePath, MovieDao movieDAO) throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] fields = line.split(",");
      int id = Integer.parseInt(fields[0]);
      String title = fields[1];
      int genreId = Integer.parseInt(fields[2]);
      Movie movie = new Movie(id, title, genreId);
      movieDAO.addMovie(movie);
    }
    reader.close();
  }
}
