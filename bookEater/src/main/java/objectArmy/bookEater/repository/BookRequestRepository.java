package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

    List<BookRequest> findByRequesteeId(Long id);

    List<BookRequest> findByBookOffer(BookOffer offer);

    List<BookRequest> findAllByBookOfferId(Long id);
}
