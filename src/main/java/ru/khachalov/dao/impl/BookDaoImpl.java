package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.khachalov.dao.BookDao;
import ru.khachalov.entity.BookEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBook(BookEntity bookEntity, Session session){
        session.save(bookEntity);
    }

    @Override
    public void displayBooks(Session session){
        List<BookEntity> resultBookEntity = session.createQuery("from BookEntity").list();
        for ( BookEntity bk : resultBookEntity) {
            System.out.println( bk.toString());
        }
    }

    @Override
    public void deleteBook(BookEntity bookEntity, Session session){
        session.remove(bookEntity);
    }

    @Override
    public void deleteBookById(int id, Session session){
        BookEntity bookEntity = session.load(BookEntity.class, id);
        session.remove(bookEntity);
    }

    @Override
    public BookEntity getBookById(int id, Session session){
        BookEntity bookEntity = session.get(BookEntity.class, id);
        if (bookEntity != null){
            return bookEntity;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    @Override
    public Set<BookEntity> getBookByIds(Collection<Integer> ids, Session session){
        Query query = session.createQuery("from BookEntity b where b.id in (:ids)")
                .setParameter("ids", ids);
        List<BookEntity> booksList = query.list();
        return new HashSet<>(booksList);
    }

    @Override
    public List<BookEntity> getAllBooks(Session session){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        Root<BookEntity> rootEntry = cq.from(BookEntity.class);
        CriteriaQuery<BookEntity> all = cq.select(rootEntry);
        TypedQuery<BookEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
