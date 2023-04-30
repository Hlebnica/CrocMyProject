package ru.croc.Movie;

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

  public List<String> getTopMoviesByGenre(String genreName, int limit) throws SQLException {
    List<String> topMovies = new ArrayList<>();
    String query = "SELECT m.TITLE, AVG(r.rating_digit) AS avg_rating " +
        "FROM MOVIE m " +
        "INNER JOIN RATING r ON m.ID = r.MOVIE_ID " +
        "INNER JOIN GENRE g ON m.GENRE_ID = g.ID " +
        "WHERE g.GENRE_NAME = ? " +
        "GROUP BY m.TITLE " +
        "ORDER BY avg_rating DESC " +
        "LIMIT ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, genreName);
    statement.setInt(2, limit);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      String title = resultSet.getString("TITLE");
      double avgRating = resultSet.getDouble("avg_rating");
      topMovies.add(title + " (" + avgRating + ")");
    }
    resultSet.close();
    statement.close();
    return topMovies;
  }


}
