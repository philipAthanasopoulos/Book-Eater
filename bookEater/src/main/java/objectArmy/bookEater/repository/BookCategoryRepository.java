package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    BookCategory findByName(String name);

    BookCategory getByName(String crime);
}
