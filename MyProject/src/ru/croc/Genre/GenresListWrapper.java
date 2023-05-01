package ru.croc.Genre;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/** Обертка XML для жанров
 *
 */
@XmlRootElement(name = "genres")
public class GenresListWrapper {

  private List<Genre> genres;

  public GenresListWrapper() {}

  public GenresListWrapper(List<Genre> genres) {
    this.genres = genres;
  }

  @XmlElement(name = "genre")
  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }
}
