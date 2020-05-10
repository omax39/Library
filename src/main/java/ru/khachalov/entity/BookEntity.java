package ru.khachalov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book", schema = "srv164864_admin")
public class BookEntity {

    public BookEntity(String name, String genre, int year, int numOfPages, Set<AuthorEntity> authors) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.numOfPages = numOfPages;
        this.authors = authors;
    }
    public BookEntity(String name, String genre, int year, int numOfPages) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.numOfPages = numOfPages;
    }
    public BookEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private int id;

    @Basic
    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Basic
    @Column(name = "genre")
    @Setter
    @Getter
    private String genre;

    @Basic
    @Column(name = "year")
    @Setter
    @Getter
    private Integer year;

    @Basic
    @Column(name = "num_of_pages")
    @Setter
    @Getter
    private Integer numOfPages;

    @Setter
    @Getter
    @ManyToMany(mappedBy = "books")
    private Set<AuthorEntity> authors = new HashSet<>();

    public void addAuthor(AuthorEntity authorEntity){
        authors.add(authorEntity);
        authorEntity.getBooks().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity bookEntity = (BookEntity) o;
        return id == bookEntity.id &&
                Objects.equals(name, bookEntity.name) &&
                Objects.equals(genre, bookEntity.genre) &&
                Objects.equals(year, bookEntity.year) &&
                Objects.equals(numOfPages, bookEntity.numOfPages);
    }

    @Override
    public String toString() {
        String s = "";
        for (AuthorEntity a : authors){
            s = s + "( " +
                    a.getName() + " " +
                    a.getFamily() + ", " +
                    a.getYear() +
                    " )";
        }
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", numOfPages=" + numOfPages +
                ", authors=" + s +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, year, numOfPages);
    }
}
