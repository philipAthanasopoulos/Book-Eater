package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import objectArmy.bookEater.entity.user.UserProfile;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

class BookRequestServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    BookRequestService bookRequestService;
    @Autowired
    BookOfferService bookOfferService;
    @Autowired
    BookRequestRepository bookRequestRepository;
    private UserProfile user1;
    private UserProfile user2;
    private UserProfile user3;
    private BookOffer bookOffer;

    @BeforeEach
    void setUp() {
        user1 = new UserProfile("aFirstName", "aLastName", LocalDate.now(), "someone@gmail.com", "password");
        user2 = new UserProfile("aFirstName", "aLastName", LocalDate.now(), "someoneElse@gmail.com", "password");
        user3 = new UserProfile("aFirstName", "aLastName", LocalDate.now(), "someoneAnotherElse@gmail.com", "password");
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

        Book offeredBook = new Book(new ArrayList<>(), "Title", "Summary", new ArrayList<>());
        bookOffer = new BookOffer(user1, offeredBook, "Description", new Date());
        bookOfferService.saveBookOffer(bookOffer);
    }

    @Test
    @Transactional
    void saveBookRequest() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        assertEquals(user1.getIncomingBookRequests().get(0), bookRequest);
        assertEquals(user2.getOutgoingBookRequests().get(0), bookRequest);
        assertEquals(bookOffer.getRequests().get(0), bookRequest);
    }

    @Test
    @Transactional
    void deleteBookRequest() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        bookRequestService.deleteBookRequest(bookRequest);
        assertTrue(user1.getIncomingBookRequests().isEmpty());
        assertTrue(user2.getOutgoingBookRequests().isEmpty());
        assertTrue(bookOffer.getRequests().isEmpty());
    }

    @Test
    @Transactional
    void getBookRequestById() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        BookRequest bookRequest1 = bookRequestService.getBookRequestById(1L);
        assertEquals(bookRequest, bookRequest1);
    }

    @Test
    @Transactional
    void deleteBookRequestById() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        bookRequestService.deleteBookRequestById(1L);
        assertTrue(user1.getIncomingBookRequests().isEmpty());
        assertTrue(user2.getOutgoingBookRequests().isEmpty());
        assertTrue(bookOffer.getRequests().isEmpty());
    }


    @Test
    @Transactional
    void acceptRequest() {
        BookRequest bookRequestToAccept = new BookRequest(user2, bookOffer);
        BookRequest bookRequestToBeDeclined = new BookRequest(user3, bookOffer);
        bookRequestService.saveBookRequest(bookRequestToAccept);
        bookRequestService.saveBookRequest(bookRequestToBeDeclined);
        bookRequestService.acceptRequest(bookRequestToAccept.getId());
        assertNull(bookRequestService.getBookRequestById(bookRequestToBeDeclined.getId()));
        assertEquals(1, user2.getNotifications().size());
        assertEquals("Your request for Title has been accepted!", user2.getNotifications().get(0));
    }
}