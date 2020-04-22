package ru.khachalov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.entity.Library;
import ru.khachalov.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Library library = new Library();
        library.setId(1);
        library.setName("NewLibrary");
        library.setAdress("Adress");
        session.save(library);
        session.getTransaction().commit();
        session.close();
    }
}
