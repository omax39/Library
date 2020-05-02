package ru.khachalov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import java.util.List;

public class Dao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void addLibrary(Library library){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(library);
        session.getTransaction().commit();
        session.close();
    }

    public void addBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    public void addAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }

    public void displayLibraries(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Library" ).list();
        for ( Library lib : (List<Library>) result ) {
            System.out.println( lib.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void displayBooks(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List resultBook = session.createQuery("from Book").list();
        for ( Book bk : (List<Book>) resultBook ) {
            System.out.println( bk.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void displayAuthors(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List resultAuthor = session.createQuery("from Author ").list();
        for ( Author auth : (List<Author>) resultAuthor ) {
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

    public void deleteBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteLibrary(Library library){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(library);
        session.getTransaction().commit();
        session.close();
    }



}
