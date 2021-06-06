package lld.librarymanagementsystem;

import java.util.Date;
import java.util.List;

public interface Search {
    public List<Book> searchByTitle(String title);

    public List<Book> searchByAuthor(String author);

    public List<Book> searchBySubject(String subject);

    public List<Book> searchByPubDate(Date publishDate);
}
