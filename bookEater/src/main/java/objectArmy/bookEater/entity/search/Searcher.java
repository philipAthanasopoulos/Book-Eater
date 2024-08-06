package objectArmy.bookEater.entity.search;

import lombok.NoArgsConstructor;
import lombok.Setter;
import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Setter
@NoArgsConstructor
@Component
public class Searcher {
    private SearchStrategy searchStrategy;


    public Searcher(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<BookOffer> searchApproximately(String query) {
        return searchStrategy.searchApproximately(query);
    }

    public List<BookOffer> searchExact(String query) {
        return searchStrategy.searchExact(query);
    }
}
