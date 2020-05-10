package ru.khachalov.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dto.Author;
import ru.khachalov.dto.Book;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;
import ru.khachalov.service.BookService;
import ru.khachalov.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    private BookDao bookDao;



    @Override
    public List<Book> getList() {
        session = sessionFactory.openSession();
        List<BookEntity> books = bookDao.getAllBooks(session);
        List<Book> bookList = new ArrayList<>();
        for (BookEntity be : books){
            Book book = new Book();
            book.setId(be.getId());
            book.setName(be.getName());
            book.setGenre(be.getGenre());
            book.setYear(be.getYear());
            book.setNumOfPages(be.getNumOfPages());
            book.setAuthors(be.getAuthors());
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public void add(Book book) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        bookDao.addBook(new BookEntity(book.getName(), book.getGenre(),
                book.getYear(), book.getNumOfPages()), session);
        session.getTransaction().commit();
        session.close();
    }
}
