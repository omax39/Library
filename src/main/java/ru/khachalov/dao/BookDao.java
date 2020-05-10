package ru.khachalov.dao;

import org.hibernate.Session;
import ru.khachalov.entity.BookEntity;
import java.util.List;

public interface BookDao {

    void addBook(BookEntity bookEntity, Session session);

    void displayBooks(Session session);

    void deleteBook(BookEntity bookEntity, Session session);

    void deleteBookById(int id, Session session);

    BookEntity getBookById(int id, Session session);

    List<BookEntity> getAllBooks(Session session);
}
