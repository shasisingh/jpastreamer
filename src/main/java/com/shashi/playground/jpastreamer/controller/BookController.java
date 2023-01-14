package com.shashi.playground.jpastreamer.controller;

import com.shashi.playground.jpastreamer.domain.Book;
import com.shashi.playground.jpastreamer.domain.Book$;
import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/api/v1/jpaStreamer")
public class BookController {

    private final JPAStreamer jpaStreamer;

    public BookController(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    @GetMapping("/books")
    public ResponseEntity<?> findAllBooks() {
        return ResponseEntity.ok(
                jpaStreamer.stream(Book.class)
                .sorted(Book$.id.reversed())
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/ids")
    public ResponseEntity<?> findBookIds() {
        final LongStream ids = jpaStreamer.stream(Book.class)
                .mapToLong(Book$.id.asLong());
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/books/{title}")
    public ResponseEntity<?> findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(
                jpaStreamer.stream(Book.class)
                .filter(Book$.title.contains(title))
                .collect(Collectors.toList()));
    }


    @GetMapping("/books/config/{title}")
    public ResponseEntity<?> findBookByTitleUsingStreamConfig(@PathVariable String title) {
        StreamConfiguration<Book> bookStreamConfiguration = StreamConfiguration
                .of(Book.class)
                .selecting(Projection.select(Book$.id, Book$.title));

        return ResponseEntity.ok(
                jpaStreamer
                        .stream(bookStreamConfiguration)
                        .filter(Book$.id.greaterThan(2L))
                        .collect(Collectors.toList()));
    }



}
