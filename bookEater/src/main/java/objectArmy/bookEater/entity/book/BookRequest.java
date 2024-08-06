package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objectArmy.bookEater.entity.user.UserProfile;

/**
 * @author Philip Athanasopoulos
 */
@Setter
@Getter
@Entity
// a user can only request a book once
@Table(name = "book_request",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"requestee_id", "book_offer_id"})
        }
)

@NoArgsConstructor
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private UserProfile requestee;
    @ManyToOne
    private BookOffer bookOffer;

    public BookRequest(UserProfile requestee, BookOffer bookOffer) {
        this.requestee = requestee;
        this.bookOffer = bookOffer;
    }
}
