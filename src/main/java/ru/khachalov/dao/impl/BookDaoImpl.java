package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.BookDao;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDaoImpl implements BookDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void addBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    public void displayBooks(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> resultBook = session.createQuery("from Book").list();
        for ( Book bk : resultBook ) {
            System.out.println( bk.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBookById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.load(Book.class, id);
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    public Book getBookById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.getTransaction().commit();
        session.close();
        if (book != null){
            return book;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    public List<Book> getAllBooks(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);

        TypedQuery<Book> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
