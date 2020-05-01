package ru.khachalov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author", schema = "srv164864_admin")
public class Author {

    public Author(String name, String family, int year, Book book) {
        this.name = name;
        this.family = family;
        this.year = year;
        addBook(book);
    }
    public Author(String name, String family, int year) {
        this.name = name;
        this.family = family;
        this.year = year;
    }
    public Author() {
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
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book){
        books.add(book);
        book.getAuthors().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(family, author.family) &&
                Objects.equals(year, author.year);
    }

    @Override
    public String toString() {
        String s = "";
        for (Book b : books){
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
