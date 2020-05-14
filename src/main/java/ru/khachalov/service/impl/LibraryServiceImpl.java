package ru.khachalov.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.khachalov.dto.Library;
import ru.khachalov.service.LibraryService;
import ru.khachalov.utils.HibernateUtils;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;

    @Override
    public List<Library> getList() {
        return null;
    }

    @Override
    public void add(Library library) {

    }
}
