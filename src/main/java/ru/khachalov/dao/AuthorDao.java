package ru.khachalov.dao;

import org.hibernate.Session;
import ru.khachalov.entity.AuthorEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AuthorDao {

    void addAuthor(AuthorEntity authorEntity, Session session);

    void displayAuthors(Session session);

    void deleteAuthor(AuthorEntity authorEntity, Session session);

    AuthorEntity getAuthorById(int id, Session session);

    Set<AuthorEntity> getAuthorByIds(Collection<Integer> ids, Session session);

    List<AuthorEntity> getAllAuthors(Session session);

    void deleteAuthorById(int id, Session session);

}
