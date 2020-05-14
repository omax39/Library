package ru.khachalov.mappers;

import org.hibernate.Session;
import ru.khachalov.dao.AuthorDao;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dto.Author;
import ru.khachalov.dto.Book;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

    private AuthorDao authorDao;

    public BookMapper(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Book entityToDto(BookEntity bookEntity){
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setGenre(bookEntity.getGenre());
        book.setYear(bookEntity.getYear());
        book.setNumOfPages(bookEntity.getNumOfPages());
        book.setAuthorsIds(bookEntity.getAuthors().stream()
                .map(AuthorEntity::getId)
                .collect(Collectors.toSet()));
        return book;
    }

    public BookEntity dtoToEntity(Book book, Session session){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setName(book.getName());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setYear(book.getYear());
        bookEntity.setNumOfPages(book.getNumOfPages());
        Set<AuthorEntity> authors = authorDao.getAuthorByIds(book.getAuthorsIds(),session);
        bookEntity.setAuthorsFromOut(authors);
        return bookEntity;
    }

}
