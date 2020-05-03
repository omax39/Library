package ru.khachalov.dao;

import ru.khachalov.entity.Library;
import java.util.List;

public interface LibraryDao {

    void addLibrary(Library library);

    void displayLibraries();

    void deleteLibrary(Library library);

    void deleteLibraryById(int id);

    Library getLibraryById(int id);

    List<Library> getAllLibraries();
}
