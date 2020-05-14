package ru.khachalov.service;

import ru.khachalov.dto.Book;

import java.util.List;

public interface BookService {

    List<Book> getList();

    void add(Book book);

    void displayBooks(Book book);

}
