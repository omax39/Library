package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void addAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }

    public void displayAuthors(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Author> resultAuthor = session.createQuery("from Author ").list();
        for ( Author auth : resultAuthor ) {
            System.out.println(auth.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(author);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAuthorById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = session.load(Author.class, id);
        session.remove(author);
        session.getTransaction().commit();
        session.close();
    }

    public Author getAuthorById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = session.get(Author.class, id);
        session.getTransaction().commit();
        session.close();
        if (author != null){
            return author;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    public List<Author> getAllAuthors(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> rootEntry = cq.from(Author.class);
        CriteriaQuery<Author> all = cq.select(rootEntry);

        TypedQuery<Author> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

}
