package ru.khachalov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.Dao;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;

import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        Library library = new Library("CentralLibrary", "Moscow");
        Author author = new Author("John", "Smith", 1987);
        Author author1 = new Author("Mark", "Josh", 1977);
        Book book = new Book("Astronomy", "Sience",
                2006, 156, new Author[]{author, author1});
        dao.addLibrary(library);
        dao.addBook(book);
        dao.addAuthor(author);
        dao.addAuthor(author1);

        dao.displayLibraries();
        dao.displayAuthors();
        dao.displayBooks();

    }
}
