package ru.khachalov.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "library", schema = "srv164864_admin")
public class Library {

    public Library() {
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
    @Column(name = "address")
    @Setter
    @Getter
    private String address;

    public Library(String name,String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id == library.id &&
                Objects.equals(name, library.name) &&
                Objects.equals(address, library.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                '}';
    }
}
