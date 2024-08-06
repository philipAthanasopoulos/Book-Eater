package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Author;
import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.repository.AuthorRepository;
import objectArmy.bookEater.repository.BookCategoryRepository;
import objectArmy.bookEater.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookServiceTest {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @BeforeEach
    void setUp() {
        ArrayList<Author> authors1 = new ArrayList<>();
        authors1.add(new Author("J.K. Rowling"));
        ArrayList<BookCategory> categories1 = new ArrayList<>();
        categories1.add(new BookCategory("Fantasy"));
        Book book1 = new Book(authors1, "Harry Potter and the Philosopher's Stone", "A young wizard's journey begins", categories1);

        ArrayList<Author> authors2 = new ArrayList<>();
        authors2.add(new Author("George R.R. Martin"));
        ArrayList<BookCategory> categories2 = new ArrayList<>();
        categories2.add(new BookCategory("Comedy"));
        Book book2 = new Book(authors2, "A Game of Thrones", "Intrigue and power struggles in a fantasy world", categories2);

        authorRepository.saveAll(authors1);
        authorRepository.saveAll(authors2);

        bookCategoryRepository.saveAll(categories1);
        bookCategoryRepository.saveAll(categories2);

        bookRepository.save(book1);
        bookRepository.save(book2);

    }

    @Test
    void getBooks() {
        List<Book> books = bookService.getBooks();
        assertEquals(2, books.size());
        assertEquals("Harry Potter and the Philosopher's Stone", books.get(0).getTitle());
        assertEquals("A Game of Thrones", books.get(1).getTitle());
    }

    @Test
    @Transactional
    void saveBook() {
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("J.R.R. Tolkien"));
        ArrayList<BookCategory> categories = new ArrayList<>();
        categories.add(new BookCategory("Fantasy"));
        Book book = new Book(authors, "The Lord of the Rings", "A hobbit's journey to destroy a powerful ring", categories);
        bookService.saveBook(book);

        assertNotNull(bookRepository.findById(1L));
    }
}