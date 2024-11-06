package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Author;
import objectArmy.bookEater.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        Author author1 = new Author("George");
        Author author2 = new Author("Mike");
        authorRepository.save(author1);
        authorRepository.save(author2);
    }

    @Test
    void getAuthors() {
        List<Author> authors = authorService.getAuthors();

        assertEquals(2, authors.size());
        assertEquals(authors.get(0).getName(), "George");
        assertEquals(authors.get(1).getName(), "Mike");
    }

    @Test
    void getAuthorByName() {
        assertNotNull(authorRepository.getByName("George"));
    }

    @Test
    void getAuthorById() {
        assertNotNull(authorService.getAuthorById(1L));
    }

    @Test
    void addAuthor() {
        authorService.addAuthor(new Author("John"));
        assertNotNull(authorRepository.getByName("John"));
    }

    @Test
    void getAuthorOrElseCreate() {
        authorService.getAuthorOrElseCreate("Nick");
        assertNotNull(authorRepository.getByName("Nick"));
    }
}