package com.shashi.playground.jpastreamer;

import com.shashi.playground.jpastreamer.domain.Author;
import com.shashi.playground.jpastreamer.domain.Book;
import com.shashi.playground.jpastreamer.repository.AuthorRepository;
import com.shashi.playground.jpastreamer.repository.BookRepository;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@AllArgsConstructor
public class SpringDataJpaStreamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaStreamerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return values -> {
            authorRepository.save(new Author(CustomDataFaker.getAuthor(), bookRepository.saveAll(getBooks(5))));
            authorRepository.save(new Author(CustomDataFaker.getAuthor(), bookRepository.saveAll(getBooks(4))));
            authorRepository.save(new Author(CustomDataFaker.getAuthor(), bookRepository.saveAll(getBooks(3))));
            authorRepository.save(new Author(CustomDataFaker.getAuthor(), bookRepository.saveAll(getBooks(2))));

        };

    }

    static final class CustomDataFaker {

        private static final Faker faker = new Faker();

        private CustomDataFaker() {
        }


        static String getBookTitle() {
            return faker.book().title();
        }

        static String getAuthor() {
            return faker.book().author();
        }

        static String getPublisher() {
            return faker.book().publisher();
        }

        static String getGenre() {
            return faker.book().genre();
        }

    }

    List<Book> getBooks(int numberOfBook) {
        return IntStream.range(0, numberOfBook)
                .mapToObj(v -> new Book(CustomDataFaker.getBookTitle(), CustomDataFaker.getGenre(), CustomDataFaker.getPublisher()))
                .collect(Collectors.toList());
    }

}
