package ru.khachalov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author", schema = "srv164864_admin")
public class AuthorEntity {

    public AuthorEntity(String name, String family, int year, Set<BookEntity> books) {
        this.name = name;
        this.family = family;
        this.year = year;
        for (BookEntity b : books){
            addBook(b);
        }
    }

    public AuthorEntity(int id, String name, String family, int year) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.year = year;
    }

    public AuthorEntity(String name, String family, int year) {
        this.name = name;
        this.family = family;
        this.year = year;
    }


    public AuthorEntity() {
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
    @Column(name = "family")
    @Setter
    @Getter
    private String family;

    @Basic
    @Column(name = "year")
    @Setter
    @Getter
    private Integer year;

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_to_author",  joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<BookEntity> books = new HashSet<>();

    public void addBook(BookEntity bookEntity){
        books.add(bookEntity);
        bookEntity.getAuthors().add(this);
    }

    public void setBooksFromOut(Set<BookEntity> books){
        for (BookEntity b : books){
            addBook(b);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity authorEntity = (AuthorEntity) o;
        return id == authorEntity.id &&
                Objects.equals(name, authorEntity.name) &&
                Objects.equals(family, authorEntity.family) &&
                Objects.equals(year, authorEntity.year);
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
                ", books=" + s +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family, year);
    }
}
