package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Philip Athanasopoulos
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
