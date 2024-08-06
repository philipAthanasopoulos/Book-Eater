package objectArmy.bookEater.entity.recommend;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Component
@Primary
public class CategoryBasedRecommendStrategy implements BookOfferRecommendStrategy {

    BookOfferService bookOfferService;

    @Autowired
    public CategoryBasedRecommendStrategy(BookOfferService bookOfferService) {
        this.bookOfferService = bookOfferService;
    }

    @Override
    public List<BookOffer> getRecommendedOffers(UserProfile userProfile) {
        List<BookOffer> results = new ArrayList<>();
        for (BookCategory favoriteCategory : userProfile.getFavoriteCategories()) {
            results.addAll(bookOfferService.getOffersByCategory(favoriteCategory));
        }
        return results;
    }
}
