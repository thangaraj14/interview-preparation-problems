package lld.moviebooking;

import lld.librarymanagementsystem.Book;
import lld.librarymanagementsystem.Search;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalog implements Search {
    HashMap<String, List<Movie>> movieTitles;
    HashMap<String, List<Movie>> movieLanguages;
    HashMap<String, List<Movie>> movieGenres;
    HashMap<Date, List<Movie>> movieReleaseDates;
    HashMap<String, List<Movie>> movieCities;

    public List<Book> searchByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> searchBySubject(String subject) {
        return null;
    }

    @Override
    public List<Book> searchByPubDate(Date publishDate) {
        return null;
    }

    public List<Movie> searchByLanguage(String language) {
        return movieLanguages.get(language);
    }

    // ...

    public List<Movie> searchByCity(String cityName) {
        return movieCities.get(cityName);
    }

    //    @Override
    public List<Movie> searchByGenre(String genre) {
        // TODO Auto-generated method stub
        return null;
    }

    //    @Override
    public List<Movie> searchByReleaseDate(Date relDate) {
        // TODO Auto-generated method stub
        return null;
    }
}