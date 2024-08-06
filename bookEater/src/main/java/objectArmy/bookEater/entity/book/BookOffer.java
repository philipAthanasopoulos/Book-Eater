package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objectArmy.bookEater.entity.user.UserProfile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_offer")
@NoArgsConstructor
@Getter
@Setter
public class BookOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private UserProfile offeror;
    @OneToOne
    private Book offeredBook;
    private String offerDescription;
    @OneToMany
    private List<BookRequest> requests;
    private Date postDate;


    public BookOffer(UserProfile offeror, Book offeredBook, String offerDescription, Date postDate) {
        this.offeror = offeror;
        this.offeredBook = offeredBook;
        this.offerDescription = offerDescription;
        this.requests = new ArrayList<>();
        this.postDate = postDate;
    }

    public String getBookTitle() {
        return this.offeredBook.getTitle();
    }

    public String getOfferorFullName() {
        return this.offeror.getFullName();
    }

    @Override
    public String toString() {
        return "BookOffer{" + "id=" + this.id + "offeror=" + offeror.getFirstName() + ", offeredBook=" + offeredBook.toString() + ", offerDescription='" + offerDescription + '\'' + ", requests=" + requests + ", postDate=" + postDate + '}';
    }

    public void addRequest(BookRequest request) {
        this.requests.add(request);
    }

    public void removeRequest(BookRequest request) {
        this.requests.remove(request);
    }
}
