package ru.khachalov.dao;

import ru.khachalov.entity.LibraryEntity;
import java.util.List;

public interface LibraryDao {

    void addLibrary(LibraryEntity libraryEntity);

    void displayLibraries();

    void deleteLibrary(LibraryEntity libraryEntity);

    void deleteLibraryById(int id);

    LibraryEntity getLibraryById(int id);

    List<LibraryEntity> getAllLibraries();
}
