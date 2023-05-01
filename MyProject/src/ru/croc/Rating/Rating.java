package ru.croc.Rating;

public class Rating {

  private int id;
  private int userId;
  private int movieId;
  private int ratingDigit;

  public Rating(int id, int userId, int movieId, int ratingDigit) {
    this.id = id;
    this.userId = userId;
    this.movieId = movieId;
    this.ratingDigit = ratingDigit;
  }

  public Rating(int userId, int movieId, int ratingDigit) {
    this.userId = userId;
    this.movieId = movieId;
    this.ratingDigit = ratingDigit;
  }



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public int getRatingDigit() {
    return ratingDigit;
  }

  public void setRatingDigit(int ratingDigit) {
    this.ratingDigit = ratingDigit;
  }
}
