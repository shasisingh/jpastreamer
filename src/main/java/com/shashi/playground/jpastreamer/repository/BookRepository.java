package com.shashi.playground.jpastreamer.repository;

import com.shashi.playground.jpastreamer.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO Class description
 *
 * @author shasisingh
 * @since 14/01/2023
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
