package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dao.LibraryDao;
import ru.khachalov.entity.LibraryEntity;
import ru.khachalov.utils.HibernateUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LibraryDaoImpl implements LibraryDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void addLibrary(LibraryEntity libraryEntity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(libraryEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void displayLibraries(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<LibraryEntity> result = session.createQuery( "from LibraryEntity" ).list();
        for ( LibraryEntity lib : result ) {
            System.out.println(lib.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteLibrary(LibraryEntity libraryEntity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(libraryEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteLibraryById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LibraryEntity libraryEntity = session.load(LibraryEntity.class, id);
        session.remove(libraryEntity);
        session.getTransaction().commit();
        session.close();
    }

    public LibraryEntity getLibraryById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LibraryEntity libraryEntity = session.get(LibraryEntity.class, id);
        session.getTransaction().commit();
        session.close();
        if (libraryEntity != null){
            return libraryEntity;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    public List<LibraryEntity> getAllLibraries(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<LibraryEntity> cq = cb.createQuery(LibraryEntity.class);
        Root<LibraryEntity> rootEntry = cq.from(LibraryEntity.class);
        CriteriaQuery<LibraryEntity> all = cq.select(rootEntry);

        TypedQuery<LibraryEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

}
