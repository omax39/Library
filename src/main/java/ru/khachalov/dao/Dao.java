package ru.khachalov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        List<Library> result = session.createQuery( "from Library" ).list();
        for ( Library lib : result ) {
            System.out.println(lib.toString());
        }
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

    public void deleteAuthorById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = session.load(Author.class, id);
        session.remove(author);
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

    public void deleteLibraryById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Library library = session.load(Library.class, id);
        session.remove(library);
        session.getTransaction().commit();
        session.close();
    }

    public Library getLibraryById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Library library = session.get(Library.class, id);
        session.getTransaction().commit();
        session.close();
        if (library != null){
            return library;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
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

    public List<Library> getAllLibraries(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Library> cq = cb.createQuery(Library.class);
        Root<Library> rootEntry = cq.from(Library.class);
        CriteriaQuery<Library> all = cq.select(rootEntry);

        TypedQuery<Library> allQuery = session.createQuery(all);
        return allQuery.getResultList();
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
