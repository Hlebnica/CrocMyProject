package Genre;

public class Genre {

  private int id;
  private String genre_name;

  public Genre(int id, String genre_name) {
    this.id = id;
    this.genre_name = genre_name;
  }

  public Genre(String genre_name) {
    this.genre_name = genre_name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGenre_name() {
    return genre_name;
  }

  public void setGenre_name(String genre_name) {
    this.genre_name = genre_name;
  }
}
