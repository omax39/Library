package ru.khachalov;

import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dao.LibraryDao;
import ru.khachalov.dao.impl.AuthorDaoImpl;
import ru.khachalov.dao.impl.BookDaoImpl;
import ru.khachalov.dao.impl.LibraryDaoImpl;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;

import ru.khachalov.entity.Library;

public class Main {
    public static void main(String[] args) {
        BookDao daoBook = new BookDaoImpl();
        AuthorDao daoAuthor = new AuthorDaoImpl();
        LibraryDao daoLibrary = new LibraryDaoImpl();
        Library library = new Library("CentralLibrary", "Moscow");
        Author author = new Author("John", "Smith", 1987);
        Author author1 = new Author("Mark", "Josh", 1977);
        Book book = new Book("Astronomy", "Sience",
                2006, 156, new Author[]{author, author1});
        daoLibrary.addLibrary(library);
        daoBook.addBook(book);
        daoAuthor.addAuthor(author);
        daoAuthor.addAuthor(author1);

        daoLibrary.displayLibraries();
        daoAuthor.displayAuthors();
        daoBook.displayBooks();

    }
}
