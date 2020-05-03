package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.LibraryDao;
import ru.khachalov.entity.Author;
import ru.khachalov.entity.Book;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LibraryDaoImpl implements LibraryDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void addLibrary(Library library){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(library);
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

    public void deleteLibrary(Library library){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(library);
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

    public List<Library> getAllLibraries(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Library> cq = cb.createQuery(Library.class);
        Root<Library> rootEntry = cq.from(Library.class);
        CriteriaQuery<Library> all = cq.select(rootEntry);

        TypedQuery<Library> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

}
