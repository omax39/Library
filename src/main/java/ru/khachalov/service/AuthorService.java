package ru.khachalov.service;

import ru.khachalov.dto.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getList();

    void add(Author author);

}
