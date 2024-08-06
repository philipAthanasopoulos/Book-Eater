package objectArmy.bookEater.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private String password;
    private String bio;
    @ManyToMany
    private List<BookCategory> favoriteCategories;
    @OneToMany
    private List<BookOffer> bookOffers;
    @OneToMany
    private List<BookRequest> outgoingBookRequests;

    @ElementCollection
    private List<String> notifications;

    public UserProfile() {
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
    }

    public UserProfile(String firstName, String lastName, LocalDate dateOfBirth, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
        this.favoriteCategories = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserProfile{" + "id=" + id + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", email='" + email + '\'' + '}';
    }

    public void addBookOffer(BookOffer bookOffer) {
        this.bookOffers.add(bookOffer);
    }

    public void addOutgoingBookRequest(BookRequest bookRequest) {
        this.outgoingBookRequests.add(bookRequest);
    }

    public void removeBookOffer(BookOffer bookOffer) {
        this.bookOffers.remove(bookOffer);
    }

    public void removeOutgoingBookRequest(BookRequest bookRequest) {
        if (this.outgoingBookRequests.remove(bookRequest)) log.info("Request removed from" + this.getFullName());
        else log.error("Request not found in " + this.getFullName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public List<BookRequest> getIncomingBookRequests() {
        List<BookRequest> incomingBookRequests = new ArrayList<>();
        for (BookOffer bookOffer : this.bookOffers) {
            incomingBookRequests.addAll(bookOffer.getRequests());
        }
        return incomingBookRequests;
    }

    public BookRequest getRequestById(Long requestId) {
        for (BookRequest request : this.getIncomingBookRequests()) {
            if (request.getId().equals(requestId)) return request;
        }
        return null;
    }

    public void removeIncomingBookRequestsOfBookOffer(BookOffer bookOffer) {
        List<BookRequest> requestsToRemove = new ArrayList<>();
        for (BookRequest request : this.getIncomingBookRequests()) {
            if (request.getBookOffer().equals(bookOffer)) requestsToRemove.add(request);
        }
        for (BookRequest request : requestsToRemove) {
            this.removeOutgoingBookRequest(request);
        }

    }

    public void sentNotification(String notification) {
        this.notifications.add(notification);
    }

    public void addIncomingBookRequest(BookRequest bookRequest) {
        this.outgoingBookRequests.add(bookRequest);
    }

    public void removeIncomingBookRequest(BookRequest request) {
        this.outgoingBookRequests.remove(request);
    }

    public List<BookOffer> getRequestedBookOffers() {
        List<BookOffer> requestedBookOffers = new ArrayList<>();
        for (BookRequest request : this.outgoingBookRequests) {
            requestedBookOffers.add(request.getBookOffer());
        }
        return requestedBookOffers;
    }
}
