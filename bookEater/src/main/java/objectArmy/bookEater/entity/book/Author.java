package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

}
