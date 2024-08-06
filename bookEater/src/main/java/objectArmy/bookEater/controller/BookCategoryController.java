package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping("/categories")
    public List<BookCategory> getBookCategories() {
        return this.bookCategoryService.getBookCategories();
    }

}
