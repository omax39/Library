package ru.khachalov.dao;

import ru.khachalov.entity.Book;
import java.util.List;

public interface BookDao {

    void addBook(Book book);

    void displayBooks();

    void deleteBook(Book book);

    void deleteBookById(int id);

    Book getBookById(int id);

    List<Book> getAllBooks();
}
