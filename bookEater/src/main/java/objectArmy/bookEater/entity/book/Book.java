package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToMany
    private List<Author> authors;
    private String title;
    private String summary;
    @ManyToMany
    private List<BookCategory> categories;


    public Book(List<Author> authors, String title, String summary, List<BookCategory> categories) {
        this.title = title;
        this.summary = summary;
        this.authors = authors;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Book{" + "authors=" + authors + ", title='" + title + '\'' + ", summary='" + summary + '\'' + ", categories=" + categories + '}';
    }


    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addCategory(BookCategory bookCategory) {
        this.categories.add(bookCategory);
    }
}
