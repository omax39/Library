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
        LibraryDao daoLibrary = new LibraryDaoImpl();
        LibraryEntity libraryEntity = new LibraryEntity("CentralLibrary", "Moscow");
        Author author = new Author(1,"John", "Smith", 1987);
        Author author1 = new Author(2,"Mark", "Josh", 1977);
        Book book = new Book(1,"Astronomy", "Sience",
                2006, 156);
        Book book1 = new Book(2,"Geography", "Scince", 2004, 278);
        book.setAuthorsFromOut(new Author[]{author,author1});
        book1.setAuthorsFromOut(new Author[]{author,author1});
        AuthorDao authorDao = new AuthorDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        AuthorService authorService = new AuthorServiceImpl(authorDao, bookDao);
        BookService bookService = new BookServiceImpl(authorDao, bookDao);

        authorService.add(author);
        authorService.add(author1);
        bookService.add(book);
        bookService.add(book1);


        List<Author> authorList = authorService.getList();
        for(Author a : authorList){
            authorService.displayAuthor(a);
        }
        List<Book> bookList = bookService.getList();
        for (Book b : bookList){
            bookService.displayBooks(b);
        }


    }
}
