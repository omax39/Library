package ru.khachalov.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dto.Author;
import ru.khachalov.dto.Book;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;
import ru.khachalov.mappers.BookMapper;
import ru.khachalov.service.BookService;
import ru.khachalov.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookServiceImpl implements BookService {

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;
    private AuthorDao authorDao;
    private BookDao bookDao;
    BookMapper bookMapper;

    public BookServiceImpl(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.bookMapper = new BookMapper(authorDao);
    }





    @Override
    public List<Book> getList() {
        session = sessionFactory.openSession();
        List<BookEntity> books = bookDao.getAllBooks(session);
        List<Book> bookList = new ArrayList<>();
        for (BookEntity be : books){
            Book book = bookMapper.entityToDto(be);
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public void add(Book book) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        bookDao.addBook(bookMapper.dtoToEntity(book,session), session);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void displayBooks(Book book) {
        session = sessionFactory.openSession();
        Set<AuthorEntity> authors = authorDao.getAuthorByIds(book.getAuthorsIds(), session);
        String s = "";
        for (AuthorEntity a : authors){
            s = s + "( " +
                    a.getName() + ", " +
                    a.getFamily() + ", " +
                    a.getYear() + ", " +
                    " )";
        }
        System.out.println(book.toString() + ", authors = " + s);
    }
}
