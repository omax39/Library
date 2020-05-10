package ru.khachalov;

import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dao.LibraryDao;
import ru.khachalov.dao.impl.AuthorDaoImpl;
import ru.khachalov.dao.impl.BookDaoImpl;
import ru.khachalov.dao.impl.LibraryDaoImpl;
import ru.khachalov.dto.Author;
import ru.khachalov.dto.Book;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;

import ru.khachalov.entity.LibraryEntity;
import ru.khachalov.service.AuthorService;
import ru.khachalov.service.BookService;
import ru.khachalov.service.impl.AuthorServiceImpl;
import ru.khachalov.service.impl.BookServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        BookDao daoBook = new BookDaoImpl();
//        AuthorDao daoAuthor = new AuthorDaoImpl();
        LibraryDao daoLibrary = new LibraryDaoImpl();
        LibraryEntity libraryEntity = new LibraryEntity("CentralLibrary", "Moscow");
        Author author = new Author("John", "Smith", 1987);
        Author author1 = new Author("Mark", "Josh", 1977);
        Book book = new Book("Astronomy", "Sience",
                2006, 156);
//        daoLibrary.addLibrary(libraryEntity);
//        daoBook.addBook(bookEntity);
//        daoAuthor.addAuthor(authorEntity);
//        daoAuthor.addAuthor(authorEntity1);
//
//        daoLibrary.displayLibraries();
//        daoAuthor.displayAuthors();
//        daoBook.displayBooks();
        AuthorDao authorDao = new AuthorDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        AuthorService authorService = new AuthorServiceImpl(authorDao);
        BookService bookService = new BookServiceImpl(bookDao);
        bookService.add(book);
        authorService.add(author);
        authorService.add(author1);
        book.setAuthorsFromOut(new Author[]{author,author1});
        List<Author> authorList = authorService.getList();
        for(Author a : authorList){
            System.out.println(a.toString());
        }
        List<Book> bookList = bookService.getList();
        for (Book b : bookList){
            System.out.println(b.toString());
        }


    }
}
