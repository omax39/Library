package ru.khachalov.dao;

import ru.khachalov.entity.Author;

import java.util.List;

public interface AuthorDao {

    void addAuthor(Author author);

    void displayAuthors();

    void deleteAuthor(Author author);

    Author getAuthorById(int id);

    List<Author> getAllAuthors();

    void deleteAuthorById(int id);

}
