package com.agh.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="readers")
public class Reader {

    @Id
    @GeneratedValue
    private int id;

    @Basic
    private String name;

    @Basic
    private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id == reader.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
