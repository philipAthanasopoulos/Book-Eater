package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.repository.BookCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookCategoryServiceTest {

    @Autowired
    BookCategoryService bookCategoryService;
    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @BeforeEach
    void setUp() {
        BookCategory fantasy = new BookCategory("Fantasy");
        BookCategory horror = new BookCategory("Horror");
        bookCategoryRepository.save(fantasy);
        bookCategoryRepository.save(horror);
    }

    @Test
    void getBookCategories() {
        List<BookCategory> categories = bookCategoryService.getBookCategories();
        assertEquals(categories.size(), 2);
        assertEquals(categories.get(0).getName(), "Fantasy");
        assertEquals(categories.get(1).getName(), "Horror");
    }

    @Test
    void addBookCategory() {
        bookCategoryService.addBookCategory(new BookCategory("Crime"));
        assertNotNull(bookCategoryRepository.getByName("Crime"));
    }

    @Test
    void findBookCategoryByName() {
        assertNotNull(bookCategoryService.findBookCategoryByName("Fantasy"));
    }

    @Test
    void getBookCategoryByName() {
        assertNotNull(bookCategoryService.findBookCategoryByName("Fantasy"));
    }

    @Test
    void getBookCategoryOrElseCreate() {
        bookCategoryService.getBookCategoryOrElseCreate("Mythology");
        assertNotNull(bookCategoryRepository.findByName("Mythology"));
    }
}