package Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDao {

  private final Connection connection;

  public RatingDao(Connection connection) {
    this.connection = connection;
  }

  public void addRating(Rating rating) throws SQLException {
    String query = "INSERT INTO rating (user_id, movie_id, rating_digit) VALUES (?, ?, ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, rating.getUserId());
    statement.setInt(2, rating.getMovieId());
    statement.setInt(3, rating.getRatingDigit());
    statement.executeUpdate();
    statement.close();
  }

  public void updateRating(Rating rating) throws SQLException {
    String query = "UPDATE rating SET rating_digit = ? WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, rating.getRatingDigit());
    statement.setInt(2, rating.getId());
    statement.executeUpdate();
    statement.close();
  }

  public void deleteRating(int id) throws SQLException {
    String query = "DELETE FROM rating WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    statement.executeUpdate();
    statement.close();
  }

  public Rating getRatingById(int id) throws SQLException {
    String query = "SELECT * FROM rating WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    Rating rating = null;
    if (resultSet.next()) {
      int userId = resultSet.getInt("user_id");
      int movieId = resultSet.getInt("movie_id");
      int ratingDigit = resultSet.getInt("rating_digit");
      rating = new Rating(id, userId, movieId, ratingDigit);
    }
    resultSet.close();
    statement.close();
    return rating;
  }

  public List<Rating> getAllRatings() throws SQLException {
    String query = "SELECT * FROM rating";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    List<Rating> ratings = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int userId = resultSet.getInt("user_id");
      int movieId = resultSet.getInt("movie_id");
      int ratingDigit = resultSet.getInt("rating_digit");
      Rating rating = new Rating(id, userId, movieId, ratingDigit);
      ratings.add(rating);
    }
    resultSet.close();
    statement.close();
    return ratings;
  }
}
