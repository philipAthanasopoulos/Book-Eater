package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return this.bookService.getBooks();
    }
}
