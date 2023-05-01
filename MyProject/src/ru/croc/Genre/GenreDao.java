package ru.croc.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDao {

  private final Connection connection;

  public GenreDao(Connection connection) {
    this.connection = connection;
  }

  public void addGenre(Genre genre) throws SQLException {
    String query = "INSERT INTO genre (genre_name) VALUES (?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, genre.getGenre_name());
    statement.executeUpdate();
    statement.close();
  }

  public void updateGenre(Genre genre) throws SQLException {
    String query = "UPDATE genre SET genre_name = ? WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, genre.getGenre_name());
    statement.setInt(2, genre.getId());
    statement.executeUpdate();
    statement.close();
  }

  public void deleteGenre(int id) throws SQLException {
    String query = "DELETE FROM genre WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    statement.executeUpdate();
    statement.close();
  }

  public Genre getGenreById(int id) throws SQLException {
    String query = "SELECT genre_name FROM genre WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    Genre genre = null;
    if (resultSet.next()) {
      String genre_name = resultSet.getString("genre_name");
      genre = new Genre(id, genre_name);
    }
    resultSet.close();
    statement.close();
    return genre;
  }

  public List<Genre> getAllGenres() throws SQLException {
    String query = "SELECT * FROM genre";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    List<Genre> genres = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String genre_name = resultSet.getString("genre_name");
      Genre genre = new Genre(id, genre_name);
      genres.add(genre);
    }
    resultSet.close();
    statement.close();
    return genres;
  }
}
