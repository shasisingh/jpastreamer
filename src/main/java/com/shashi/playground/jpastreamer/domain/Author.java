package com.shashi.playground.jpastreamer.domain;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@Entity(name = "author")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
