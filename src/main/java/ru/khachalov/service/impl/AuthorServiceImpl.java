package ru.khachalov.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.impl.AuthorDaoImpl;
import ru.khachalov.dto.Author;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;
import ru.khachalov.service.AuthorService;
import ru.khachalov.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    private AuthorDao authorDao;



    @Override
    public List<Author> getList() {
        session = sessionFactory.openSession();
        List<AuthorEntity> authors = authorDao.getAllAuthors(session);
        List<Author> authorList = new ArrayList<>();
        for (AuthorEntity ae : authors){
            Author author = new Author();
            author.setId(ae.getId());
            author.setName(ae.getName());
            author.setFamily(ae.getFamily());
            author.setYear(ae.getYear());
            author.setBooks(ae.getBooks());
            authorList.add(author);
        }
        return authorList;
    }

    @Override
    public void add(Author author) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        authorDao.addAuthor(new AuthorEntity(
                author.getName(), author.getFamily(),
                author.getYear()), session);
        session.getTransaction().commit();
        session.close();
    }
}
