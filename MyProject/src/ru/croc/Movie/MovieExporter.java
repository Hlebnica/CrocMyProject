package ru.croc.Movie;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;



public class MovieExporter {

  public static void exportGenresToXML(String filePath, MovieDao movieDao)
      throws SQLException, JAXBException {
    List<Movie> movies = movieDao.getAllMovies();
    MovieListWrapper movieListWrapper = new MovieListWrapper(movies);
    JAXBContext context = JAXBContext.newInstance(MovieListWrapper.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    File file = new File(filePath);
    marshaller.marshal(movieListWrapper, file);
  }
}
