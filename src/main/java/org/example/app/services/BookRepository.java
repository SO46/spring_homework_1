package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                removeBook(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String regex) {
        for (Book book : retreiveAll()) {
            if (Pattern.matches(regex, book.getTitle())) {
                removeBook(book);
            } else if (Pattern.matches(regex, book.getAuthor())) {
                removeBook(book);
            } else if (book.getSize()!= null){
                if(Pattern.matches(regex, book.getSize().toString())) {
                    removeBook(book);
                }
            }
        }
        return false;
    }

    private boolean removeBook(Book book){
        logger.info("remove book completed: " + book);
        return repo.remove(book);
    }
}
