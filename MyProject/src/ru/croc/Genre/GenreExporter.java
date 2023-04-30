package ru.croc.Genre;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class GenreExporter {

  public static void exportGenresToXML(String filePath, GenreDao genreDAO)
      throws SQLException, JAXBException {
    List<Genre> genres = genreDAO.getAllGenres();
    GenresListWrapper genresListWrapper = new GenresListWrapper(genres);
    JAXBContext context = JAXBContext.newInstance(GenresListWrapper.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    File file = new File(filePath);
    marshaller.marshal(genresListWrapper, file);
  }
}


