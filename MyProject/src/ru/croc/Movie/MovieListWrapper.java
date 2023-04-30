package ru.croc.Movie;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "movies")
public class MovieListWrapper {
  private List<Movie> movies;

  public MovieListWrapper() {}

  public MovieListWrapper(List<Movie> movies) {
    this.movies = movies;
  }

  @XmlElement(name = "movie")
  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
