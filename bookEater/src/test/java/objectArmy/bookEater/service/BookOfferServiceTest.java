package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.*;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.repository.BookOfferRepository;
import objectArmy.bookEater.repository.BookRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Philip Athanasopoulos
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

public class BookOfferServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    BookOfferService bookOfferService;
    @Autowired
    BookOfferRepository bookOfferRepository;
    @Autowired
    BookRequestRepository bookRequestRepository;
    @Autowired
    BookRequestService bookRequestService;
    @Autowired
    AuthorService authorService;
    @Autowired
    BookCategoryService bookCategoryService;
    BookOffer bookOffer;
    private UserProfile user1;
    private UserProfile user2;

    @BeforeEach
    public void setUp() {
        // Create a new UserProfile instance
        user1 = new UserProfile("aFirstName", "aLastName", LocalDate.now(), "anemail@gmail.com", "password");
        user2 = new UserProfile("aFirstName", "aLastName", LocalDate.now(), "anotheremail@gmail.com", "password");

        userService.saveUser(user1);
        userService.saveUser(user2);


        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("Author1"));
            }
        };
        Book offeredBook = new Book(authors, "Title", "Summary", new ArrayList<>() {{
            add(new BookCategory("Category1"));
        }});


        bookOffer = new BookOffer(user1, offeredBook, "Description", new Date());
    }

    @Test
    @Transactional
    void saveBookOffer() {
        bookOfferService.saveBookOffer(bookOffer);
        assertEquals(1, bookOffer.getOfferor().getId());
        assertEquals(1, bookOffer.getOfferedBook().getId());
        assertEquals(1, authorService.getAuthors().size());
        assertEquals("Author1", authorService.getAuthors().get(0).getName());
    }

    @Test
    @Transactional
    void getAllBookOffers() {
        bookOfferService.saveBookOffer(bookOffer);
        assertNotNull(bookOfferService.getAllBookOffers());
        assertEquals(1, bookOfferService.getAllBookOffers().size());
        assertEquals(bookOffer, bookOfferService.getAllBookOffers().get(0));
    }

    @Test
    @Transactional
    void getBookOfferById() {
        bookOfferService.saveBookOffer(bookOffer);
        assertEquals(bookOffer, bookOfferService.getBookOfferById(bookOffer.getId()));
    }

    @Test
    void deleteBookOfferById() {
        bookOfferService.saveBookOffer(bookOffer);
        bookOfferService.deleteBookOfferById(bookOffer.getId());
        assertEquals(0, bookOfferService.getAllBookOffers().size());
        assertEquals(0, bookRequestRepository.findAll().size());
    }

    @Test
    @Transactional
    void deleteBookOffer() {
        bookOfferService.saveBookOffer(bookOffer);
        bookRequestService.saveBookRequest(new BookRequest(user2, bookOffer));

        bookOfferService.deleteBookOffer(bookOffer);
        assertEquals(0, bookOfferService.getAllBookOffers().size());
        assertEquals(0, bookRequestRepository.findAll().size());
    }

    @Test
    @Transactional
    void searchByTitleApproximately() {
        bookOfferService.saveBookOffer(bookOffer);
        List<BookOffer> offersWithSpecificTitle = bookOfferService.searchByTitleApproximately("Ti");
        assertEquals(1, offersWithSpecificTitle.size());
        assertEquals(bookOffer, offersWithSpecificTitle.get(0));
    }

    @Test
    @Transactional
    void searchByTitleExact() {
        bookOfferService.saveBookOffer(bookOffer);
        List<BookOffer> offersWithSpecificTitle = bookOfferService.searchByTitleExact("Title");
        assertEquals(1, offersWithSpecificTitle.size());
        assertEquals(bookOffer, offersWithSpecificTitle.get(0));
    }

    @Test
    @Transactional
    void searchByAuthorApproximately() {
        bookOfferService.saveBookOffer(bookOffer);
        List<BookOffer> offersWithSpecificAuthor = bookOfferService.searchByAuthorApproximately("Auth");
        assertEquals(1, offersWithSpecificAuthor.size());
        assertEquals(bookOffer, offersWithSpecificAuthor.get(0));
    }

    @Test
    @Transactional
    void searchByAuthorExact() {
        bookOfferService.saveBookOffer(bookOffer);
        List<BookOffer> offersWithSpecificAuthor = bookOfferService.searchByAuthorExact("Author1");
        assertEquals(1, offersWithSpecificAuthor.size());
        assertEquals(bookOffer, offersWithSpecificAuthor.get(0));

    }

    @Test
    @Transactional
    void getOffersByCategory() {
        bookOfferService.saveBookOffer(bookOffer);
        BookCategory category = bookCategoryService.getBookCategories().get(0);
        List<BookOffer> offersWithSpecificCategory = bookOfferService.getOffersByCategory(category);
        assertEquals(1, offersWithSpecificCategory.size());
        assertEquals(bookOffer, offersWithSpecificCategory.get(0));
    }

    @Test
    @Transactional
    void getAllBookOffersExceptFor() {
        bookOfferService.saveBookOffer(bookOffer);
        Long idToExclude = bookOffer.getId();
        List<BookOffer> offers = bookOfferService.getAllBookOffersExceptFor(idToExclude);
        assertEquals(0, offers.size());
    }
}
