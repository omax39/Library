package ru.khachalov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;

import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Library library = new Library("CentralLibrary", "Moscow");
        session.save(library);
        Author author = new Author("John", "Smith", 1987);
        Author author1 = new Author("Mark", "Josh", 1977);
        Book book = new Book("Astronomy", "Sience",
                2006, 156, author);
        book.addAuthor(author1);
        session.save(author);
        session.save(author1);
        session.save(book);
        session.getTransaction().commit();
        session.close();

//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.createQuery("delete from Library where id = 1").executeUpdate();
//        session.getTransaction();
//        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Library" ).list();
        List resultBook = session.createQuery("from Book").list();
        List resultAuthor = session.createQuery("from Author ").list();
        for ( Library lib : (List<Library>) result ) {
            System.out.println( lib.toString());
        }
        for ( Book bk : (List<Book>) resultBook ) {
            System.out.println( bk.toString());
        }
        for ( Author auth : (List<Author>) resultAuthor ) {
            System.out.println(auth.toString());
        }
        session.getTransaction().commit();
        session.close();
    }
}
