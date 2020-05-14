package ru.khachalov.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dto.Author;
import ru.khachalov.dto.Book;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;
import ru.khachalov.mappers.AuthorMapper;
import ru.khachalov.service.AuthorService;
import ru.khachalov.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthorServiceImpl implements AuthorService {

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;
    AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.authorMapper = new AuthorMapper(bookDao);

    }

    private AuthorDao authorDao;
    private BookDao bookDao;


    @Override
    public List<Author> getList() {
        session = sessionFactory.openSession();
        List<AuthorEntity> authors = authorDao.getAllAuthors(session);
        List<Author> authorList = new ArrayList<>();
        for (AuthorEntity ae : authors){
            Author author = authorMapper.entityToDto(ae);
            authorList.add(author);
        }
        return authorList;
    }

    @Override
    public void add(Author author) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        authorDao.addAuthor(authorMapper.dtoToEntity(author, session), session);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void displayAuthor(Author author) {
        session = sessionFactory.openSession();
        Set<BookEntity> books = bookDao.getBookByIds(author.getBooksIds(), session);
        String s = "";
        for (BookEntity b : books){
            s = s + "( " +
                    b.getName() + ", " +
                    b.getGenre() + ", " +
                    b.getYear() + ", " +
                    b.getNumOfPages() +
                    " )";
        }
        System.out.println(author.toString() + ", books = " + s);
    }
}
