package Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

  private final Connection connection;

  public MovieDao(Connection connection) {
    this.connection = connection;
  }

  public void addMovie(Movie movie) throws SQLException {
    String query = "INSERT INTO movie (title, genre_id) VALUES (?, ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, movie.getTitle());
    statement.setInt(2, movie.getGenreId());
    statement.executeUpdate();
    statement.close();
  }

  public void updateMovie(Movie movie) throws SQLException {
    String query = "UPDATE movie SET title = ?, genre_id = ? WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, movie.getTitle());
    statement.setInt(2, movie.getGenreId());
    statement.setInt(3, movie.getId());
    statement.executeUpdate();
    statement.close();
  }

  public void deleteMovie(int id) throws SQLException {
    String query = "DELETE FROM movie WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    statement.executeUpdate();
    statement.close();
  }

  public Movie getMovieById(int id) throws SQLException {
    String query = "SELECT * FROM movie WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    Movie movie = null;
    if (resultSet.next()) {
      String title = resultSet.getString("title");
      int genreId = resultSet.getInt("genre_id");
      movie = new Movie(id, title, genreId);
    }
    resultSet.close();
    statement.close();
    return movie;
  }

  public List<Movie> getAllMovies() throws SQLException {
    String query = "SELECT * FROM movie";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    List<Movie> movies = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String title = resultSet.getString("title");
      int genreId = resultSet.getInt("genre_id");
      Movie movie = new Movie(id, title, genreId);
      movies.add(movie);
    }
    resultSet.close();
    statement.close();
    return movies;
  }

}
