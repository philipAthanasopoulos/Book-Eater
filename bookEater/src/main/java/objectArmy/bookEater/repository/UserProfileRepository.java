package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserByEmail(String email);

    UserProfile findUserById(Long id);
}
