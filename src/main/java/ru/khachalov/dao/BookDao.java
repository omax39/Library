package ru.khachalov.dao;

import org.hibernate.Session;
import ru.khachalov.entity.BookEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BookDao {

    void addBook(BookEntity bookEntity, Session session);

    void displayBooks(Session session);

    void deleteBook(BookEntity bookEntity, Session session);

    void deleteBookById(int id, Session session);

    BookEntity getBookById(int id, Session session);

    Set<BookEntity> getBookByIds(Collection<Integer> ids, Session session);

    List<BookEntity> getAllBooks(Session session);
}
