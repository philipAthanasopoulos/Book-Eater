package objectArmy.bookEater.entity.recommend;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Component
public class BookOfferRecommender {
    BookOfferRecommendStrategy bookOfferRecommendStrategy;

    public BookOfferRecommender(BookOfferRecommendStrategy bookOfferRecommendStrategy) {
        this.bookOfferRecommendStrategy = bookOfferRecommendStrategy;
    }

    public List<BookOffer> getRecommendedOffers(UserProfile userProfile) {
        return bookOfferRecommendStrategy.getRecommendedOffers(userProfile);
    }
}
