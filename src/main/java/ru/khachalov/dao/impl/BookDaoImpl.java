package ru.khachalov.dao.impl;

import org.hibernate.Session;
import ru.khachalov.dao.BookDao;
import ru.khachalov.entity.BookEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public void addBook(BookEntity bookEntity, Session session){
        session.save(bookEntity);
    }

    public void displayBooks(Session session){
        List<BookEntity> resultBookEntity = session.createQuery("from BookEntity").list();
        for ( BookEntity bk : resultBookEntity) {
            System.out.println( bk.toString());
        }
    }

    public void deleteBook(BookEntity bookEntity, Session session){
        session.remove(bookEntity);
    }

    public void deleteBookById(int id, Session session){
        BookEntity bookEntity = session.load(BookEntity.class, id);
        session.remove(bookEntity);
    }

    public BookEntity getBookById(int id, Session session){
        BookEntity bookEntity = session.get(BookEntity.class, id);
        if (bookEntity != null){
            return bookEntity;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    public List<BookEntity> getAllBooks(Session session){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        Root<BookEntity> rootEntry = cq.from(BookEntity.class);
        CriteriaQuery<BookEntity> all = cq.select(rootEntry);
        TypedQuery<BookEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
