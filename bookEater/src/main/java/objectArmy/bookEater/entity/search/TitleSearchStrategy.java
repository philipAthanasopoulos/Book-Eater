package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class TitleSearchStrategy implements SearchStrategy {
    private final BookOfferService bookOfferService;

    @Autowired
    public TitleSearchStrategy(BookOfferService bookOfferService) {
        this.bookOfferService = bookOfferService;
    }


    @Override
    public List<BookOffer> searchApproximately(String queryBookTitle) {
        return bookOfferService.searchByTitleApproximately(queryBookTitle);
    }

    @Override
    public List<BookOffer> searchExact(String query) {
        return bookOfferService.searchByTitleExact(query);
    }


}
