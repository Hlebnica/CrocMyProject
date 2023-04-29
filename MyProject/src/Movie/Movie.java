package Movie;

public class Movie {

  private int id;
  private String title;
  private int genreId;

  public Movie(int id, String title, int genreId) {
    this.id = id;
    this.title = title;
    this.genreId = genreId;
  }

  public Movie(String title, int genreId) {
    this.title = title;
    this.genreId = genreId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getGenreId() {
    return genreId;
  }

  public void setGenreId(int genreId) {
    this.genreId = genreId;
  }

}
