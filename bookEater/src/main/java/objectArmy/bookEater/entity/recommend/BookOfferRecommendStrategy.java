package objectArmy.bookEater.entity.recommend;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookOfferRecommendStrategy {
    List<BookOffer> getRecommendedOffers(UserProfile userProfile);
}
