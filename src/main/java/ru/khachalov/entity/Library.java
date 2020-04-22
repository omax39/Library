package ru.khachalov.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "library", schema = "srv164864_admin")
public class Library {
    private int id;
    private String name;
    private String adress;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id == library.id &&
                Objects.equals(name, library.name) &&
                Objects.equals(adress, library.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, adress);
    }
}
