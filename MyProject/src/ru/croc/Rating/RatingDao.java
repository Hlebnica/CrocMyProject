package ru.croc.Rating;

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

  public void addRatingByUserNameAndTitle(String userName, String movieTitle, int rating)
      throws SQLException {
    String query = "INSERT INTO rating (user_id, movie_id, rating_digit) " +
        "VALUES ((SELECT id FROM users WHERE user_name = ?), " +
        "(SELECT id FROM movie WHERE title = ?), ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, userName);
    statement.setString(2, movieTitle);
    statement.setInt(3, rating);
    statement.executeUpdate();
    statement.close();
  }

  public void updateRatingByUserNameAndTitle(String userName, String movieTitle, int rating)
      throws SQLException {
    String query = "UPDATE rating SET rating_digit = ? " +
        "WHERE user_id = (SELECT id FROM users WHERE user_name = ?) " +
        "AND movie_id = (SELECT id FROM movie WHERE title = ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, rating);
    statement.setString(2, userName);
    statement.setString(3, movieTitle);
    statement.executeUpdate();
    statement.close();
  }

  public List<String> getAllRatingsWithUserAndMovieNames() throws SQLException {
    List<String> ratings = new ArrayList<>();
    String query = "SELECT u.USER_NAME, m.TITLE, r.RATING_DIGIT " +
        "FROM RATING r " +
        "INNER JOIN USERS u ON r.USER_ID = u.ID " +
        "INNER JOIN MOVIE m ON r.MOVIE_ID = m.ID";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    ratings.add("Пользователь | Фильм | Оценка");
    while (resultSet.next()) {
      String userName = resultSet.getString("USER_NAME");
      String movieTitle = resultSet.getString("TITLE");
      int ratingDigit = resultSet.getInt("RATING_DIGIT");
      ratings.add(userName + " | " + movieTitle + " | " + ratingDigit);
    }
    resultSet.close();
    statement.close();
    return ratings;
  }


}


