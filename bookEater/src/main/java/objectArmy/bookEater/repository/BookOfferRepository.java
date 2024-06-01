package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookOfferRepository extends JpaRepository<BookOffer, Long> {
    BookOffer findBookOfferById(Long id);

    List<BookOffer> findByOfferedBookTitleContainingIgnoreCase(String name);

    List<BookOffer> findByOfferedBookAuthorsNameContainingIgnoreCase(String name);

    List<BookOffer> findByOfferedBookTitle(String title);

    List<BookOffer> findByOfferedBookAuthorsName(String name);

    List<BookOffer> findByOfferedBookCategoriesContaining(BookCategory category);

    List<BookOffer> findByOfferorIdNot(Long id);

}
