package objectArmy.bookEater.service;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import objectArmy.bookEater.entity.book.Author;
import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

/**
 * @author Philip Athanasopoulos
 * This class creates users and some books
 * so that the database is populated with some data
 */
@Service
public class DatabaseInitializerService {
    private final UserService userService;
    private final BookOfferService bookOfferService;
    private final BookCategoryService bookCategoryService;
    private final BookService bookService;

    @Autowired
    public DatabaseInitializerService(UserService userService, BookOfferService bookOfferService, BookCategoryService boookCategoryService, BookService bookService) {
        this.userService = userService;
        this.bookOfferService = bookOfferService;
        this.bookCategoryService = boookCategoryService;
        this.bookService = bookService;
    }

//    @PostConstruct
//    @Transactional
//    public void insertData() {
//        Faker faker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 20; i++) {
//            UserProfile user = new UserProfile();
//            user.setFirstName(faker.name().firstName());
//            user.setLastName(faker.name().lastName());
//            user.setPassword(faker.internet().password());
//            user.setEmail(faker.internet().emailAddress());
//            userService.saveUser(user);
//
//            BookOffer bookOffer = new BookOffer();
//            Book book = new Book();
//            book.setAuthors(List.of(new Author(faker.book().author())));
//            book.setTitle(faker.book().title());
//            book.setCategories(bookCategoryService.getBookCategories());
//            book.setSummary(faker.lorem().paragraph());
//
//            bookOffer.setOfferedBook(book);
//            bookOffer.setOfferor(user);
//            bookOfferService.saveBookOffer(bookOffer);
//
//        }
//
//    }


}
