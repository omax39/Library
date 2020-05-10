package ru.khachalov.dto;

import lombok.Getter;
import lombok.Setter;
import ru.khachalov.entity.BookEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Author {

    public Author(int id, String name, String family, Integer year, Set<BookEntity> books) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.year = year;
        this.books = books;
    }

    public Author(String name, String family, Integer year) {
        this.name = name;
        this.family = family;
        this.year = year;
    }

    public Author() {
    }

    private int id;

    private String name;

    private String family;

    private Integer year;

    private Set<BookEntity> books = new HashSet<>();

    public void setBooksFromOut(Book[] books) {
        for (Book b : books){
            BookEntity be = new BookEntity();
            be.setId(b.getId());
            be.setName(b.getName());
            be.setGenre(b.getGenre());
            be.setYear(b.getYear());
            be.setNumOfPages(b.getNumOfPages());
            be.setAuthors(b.getAuthors());
            this.books.add(be);
        }
    }


    @Override
    public String toString() {
        String s = "";
        for (BookEntity b : books){
            s = s + "( " +
                    b.getName() + ", " +
                    b.getGenre() + ", " +
                    b.getYear() + ", " +
                    b.getNumOfPages() +
                    " )";
        }
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", year=" + year +
                ", bookEntities=" + s +
                '}';
    }
}
