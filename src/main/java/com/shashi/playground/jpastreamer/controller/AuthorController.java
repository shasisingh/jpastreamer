package com.shashi.playground.jpastreamer.controller;

import com.shashi.playground.jpastreamer.domain.Author;
import com.shashi.playground.jpastreamer.domain.Author$;
import com.shashi.playground.jpastreamer.domain.Book;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/jpaStreamer")
public class AuthorController {

    private final JPAStreamer jpaStreamer;

    public AuthorController(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    @GetMapping("/author")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                jpaStreamer.stream(Author.class)
                .sorted(Author$.id.reversed())
                .collect(Collectors.toList()));
    }

    @GetMapping("/author/{authorLastName}")
    public ResponseEntity<?> findBookByAuthorLastName(@PathVariable String authorLastName) {
        return ResponseEntity.
                ok(jpaStreamer.stream(Book.class)
                .filter(k-> k.getAuthor().getName().contains(authorLastName))
                .collect(Collectors.toList()));
    }


}
