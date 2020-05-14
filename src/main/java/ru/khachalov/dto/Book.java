package ru.khachalov.dto;

import lombok.Getter;
import lombok.Setter;
import ru.khachalov.entity.AuthorEntity;
import ru.khachalov.entity.BookEntity;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Book {

    public Book(int id, String name, String genre, Integer year, Integer numOfPages) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.numOfPages = numOfPages;
    }

    public Book(String name, String genre, Integer year, Integer numOfPages) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.numOfPages = numOfPages;
    }

    public Book() {
    }

    private int id;

    private String name;

    private String genre;

    private Integer year;

    private Integer numOfPages;

    private Set<Integer> authorsIds = new HashSet<>();

    public void setAuthorsFromOut(Author[] authors) {
        for (Author a : authors){
            authorsIds.add(a.getId());
        }
    }

    @Override
    public String toString() {
//        String s = "";
//        for (AuthorEntity a: authors){
//            s = s + "( " +
//                    a.getName() + ", " +
//                    a.getFamily() + ", " +
//                    a.getYear() +
//                    " )";
//        }
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + genre + '\'' +
                ", year=" + year +
                ", numOfPages=" + numOfPages +
//                ", bookEntities=" + s +
                '}';
    }

}
