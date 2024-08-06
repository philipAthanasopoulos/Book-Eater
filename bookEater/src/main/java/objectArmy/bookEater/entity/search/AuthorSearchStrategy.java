package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Component
public class AuthorSearchStrategy implements SearchStrategy {
    private final BookOfferService bookOfferService;

    @Autowired
    public AuthorSearchStrategy(BookOfferService bookOfferService) {
        this.bookOfferService = bookOfferService;
    }

    @Override
    public List<BookOffer> searchApproximately(String query) {
        return bookOfferService.searchByAuthorApproximately(query);
    }

    @Override
    public List<BookOffer> searchExact(String query) {
        return bookOfferService.searchByAuthorExact(query);
    }
}
