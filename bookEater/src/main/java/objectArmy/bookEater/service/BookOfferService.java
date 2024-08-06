package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.repository.BookOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
@Transactional
public class BookOfferService {
    private final BookOfferRepository bookOfferRepository;
    private final BookService bookService;
    private final BookRequestService bookRequestService;

    //Circular dependency is resolved by using @Lazy annotation
    @Autowired
    public BookOfferService(BookOfferRepository bookOfferRepository, BookService bookService, @Lazy BookRequestService bookRequestService) {
        this.bookOfferRepository = bookOfferRepository;
        this.bookService = bookService;
        this.bookRequestService = bookRequestService;
    }


    public void saveBookOffer(BookOffer bookOffer) {
        bookService.saveBook(bookOffer.getOfferedBook());
        bookOffer.getOfferor().addBookOffer(bookOffer);
        bookOfferRepository.save(bookOffer);
    }


    public List<BookOffer> getAllBookOffers() {
        return bookOfferRepository.findAll();
    }


    public BookOffer getBookOfferById(Long id) {
        return bookOfferRepository.findBookOfferById(id);
    }

    @Transactional
    public void deleteBookOfferById(Long id) {
        deleteBookOffer(bookOfferRepository.findBookOfferById(id));
    }

    public void deleteBookOffer(BookOffer offer) {
        UserProfile offeror = offer.getOfferor();
        offeror.removeBookOffer(offer);
        //delete all requests for this offer
        bookRequestService.deleteAllBookRequestsForOffer(offer);
        //remove offer
        bookOfferRepository.delete(offer);
    }

    // Find by bookTitle
    public List<BookOffer> searchByTitleApproximately(String userQuery) {
        return this.bookOfferRepository.findByOfferedBookTitleContainingIgnoreCase(userQuery);
    }

    // Find by bookAuthor
    public List<BookOffer> searchByAuthorApproximately(String userQuery) {
        return this.bookOfferRepository.findByOfferedBookAuthorsNameContainingIgnoreCase(userQuery);
    }

    // Find by bookTitle
    public List<BookOffer> searchByTitleExact(String userQuery) {
        return this.bookOfferRepository.findByOfferedBookTitle(userQuery);
    }

    // Find by bookAuthor
    public List<BookOffer> searchByAuthorExact(String userQuery) {
        return this.bookOfferRepository.findByOfferedBookAuthorsName(userQuery);
    }

    public List<BookOffer> getOffersByCategory(BookCategory favoriteCategory) {
        return bookOfferRepository.findByOfferedBookCategoriesContaining(favoriteCategory);
    }

    public List<BookOffer> getAllBookOffersExceptFor(Long id) {
        return bookOfferRepository.findByOfferorIdNot(id);
    }
}
