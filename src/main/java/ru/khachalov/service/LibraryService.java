package ru.khachalov.service;

import ru.khachalov.dto.Library;

import java.util.List;

public interface LibraryService {

    List<Library> getList();

    void add(Library library);

}
