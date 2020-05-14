package ru.khachalov.mappers;

import com.sun.istack.Nullable;
import org.hibernate.Session;
import ru.khachalov.dao.BookDao;
import ru.khachalov.dto.Author;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {

    private BookDao bookDao;

    public AuthorMapper(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Author entityToDto(AuthorEntity authorEntity){
        Author author = new Author();
        author.setId(authorEntity.getId());
        author.setName(authorEntity.getName());
        author.setFamily(authorEntity.getFamily());
        author.setYear(authorEntity.getYear());
        author.setBooksIds(authorEntity.getBooks().stream()
                .map(BookEntity::getId)
                .collect(Collectors.toSet()));
        return author;
    }

    public AuthorEntity dtoToEntity(Author author, Session session){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setName(author.getName());
        authorEntity.setFamily(author.getFamily());
        authorEntity.setYear(author.getYear());
        Set<BookEntity> books = bookDao.getBookByIds(author.getBooksIds(),session);
        authorEntity.setBooksFromOut(books);
        return authorEntity;
    }

}
