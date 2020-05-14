package ru.khachalov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.entity.AuthorEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void addAuthor(AuthorEntity authorEntity, Session session){
        session.save(authorEntity);
    }

    @Override
    public void displayAuthors(Session session){
        List<AuthorEntity> resultAuthorEntity = session.createQuery("from AuthorEntity ").list();
        for ( AuthorEntity auth : resultAuthorEntity) {
            System.out.println(auth.toString());
        }
    }

    @Override
    public void deleteAuthor(AuthorEntity authorEntity, Session session){
        session.remove(authorEntity);
    }

    @Override
    public void deleteAuthorById(int id, Session session){
        AuthorEntity authorEntity = session.load(AuthorEntity.class, id);
        session.remove(authorEntity);
    }

    @Override
    public AuthorEntity getAuthorById(int id, Session session){
        AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
        session.getTransaction().commit();
        if (authorEntity != null){
            return authorEntity;
        } else {
            throw new IllegalArgumentException("There are no element for id in the table");
        }
    }

    @Override
    public Set<AuthorEntity> getAuthorByIds(Collection<Integer> ids, Session session){
        Query query = session.createQuery("from AuthorEntity a where a.id in (:ids)")
                .setParameter("ids", ids);
        List<AuthorEntity> authorsList = query.list();
        return new HashSet<>(authorsList);
    }

    @Override
    public List<AuthorEntity> getAllAuthors(Session session){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AuthorEntity> cq = cb.createQuery(AuthorEntity.class);
        Root<AuthorEntity> rootEntry = cq.from(AuthorEntity.class);
        CriteriaQuery<AuthorEntity> all = cq.select(rootEntry);

        TypedQuery<AuthorEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

}
