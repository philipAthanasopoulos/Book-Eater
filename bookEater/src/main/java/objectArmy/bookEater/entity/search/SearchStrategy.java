package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchStrategy {

    List<BookOffer> searchApproximately(String query);

    List<BookOffer> searchExact(String query);

}
