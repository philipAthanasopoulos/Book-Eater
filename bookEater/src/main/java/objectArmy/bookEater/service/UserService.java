package objectArmy.bookEater.service;

import lombok.NonNull;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author Philip Athanasopoulos
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserProfileRepository userProfileRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserProfile userToAdd) {
        UserProfile existingUser = userProfileRepository.findUserById(userToAdd.getId());

        if (existingUser == null) {
            String hashedPassword = passwordEncoder.encode(userToAdd.getPassword());
            userToAdd.setPassword(hashedPassword);
        }

        userProfileRepository.save(userToAdd);
    }

    public void deleteUser(UserProfile userToDelete) {
        userProfileRepository.delete(userToDelete);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile user = userProfileRepository.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public UserProfile getUserById(long id) {
        return userProfileRepository.findUserById(id);
    }

    public void updateUser(@NonNull UserProfile newUserProfile, @NonNull Long id) {
        UserProfile user = userProfileRepository.findUserById(id);

        if (isFirstNameValid(newUserProfile, user)) user.setFirstName(newUserProfile.getFirstName());

        if (isLastNameValid(newUserProfile, user)) user.setLastName(newUserProfile.getLastName());

        if (isEmailValid(newUserProfile, user)) user.setEmail(newUserProfile.getEmail());

        if (isDateOfBirthValid(newUserProfile, user)) user.setDateOfBirth(newUserProfile.getDateOfBirth());

        if (newPasswordIsValid(newUserProfile, user))
            user.setPassword(passwordEncoder.encode(newUserProfile.getPassword()));

        if (isBioValid(newUserProfile, user)) user.setBio(newUserProfile.getBio());

        if (areFavouriteCategoriesValid(newUserProfile, user)) {
            System.out.println("Categories were updated");
            user.setFavoriteCategories(newUserProfile.getFavoriteCategories());
        }

        userProfileRepository.save(user);
    }

    private boolean areFavouriteCategoriesValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getFavoriteCategories() != null && !newUserProfile.getFavoriteCategories().equals(user.getFavoriteCategories());
    }

    private boolean isBioValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getBio() != null && !newUserProfile.getBio().equals(user.getBio());
    }

    private boolean isFirstNameValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getFirstName() != null && !newUserProfile.getFirstName().equals(user.getFirstName());
    }

    private boolean isLastNameValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getLastName() != null && !newUserProfile.getLastName().equals(user.getLastName());
    }

    private boolean isEmailValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getEmail() != null && !newUserProfile.getEmail().equals(user.getEmail());
    }

    private boolean isDateOfBirthValid(UserProfile newUserProfile, UserProfile user) {
        return newUserProfile.getDateOfBirth() != null &&
                !newUserProfile.getDateOfBirth().equals(user.getDateOfBirth()) &&
                LocalDate.now().isAfter(newUserProfile.getDateOfBirth());
    }

    private boolean newPasswordIsValid(UserProfile newUserProfile, UserProfile user) {
        return (newUserProfile.getPassword() != null) &&
                !passwordEncoder.matches(newUserProfile.getPassword(), user.getPassword()) &&
                !newUserProfile.getPassword().trim().isBlank() &&
                (newUserProfile.getPassword().length() >= 8);
    }
}
