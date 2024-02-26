package toni.eatbydate.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import toni.eatbydate.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
        Optional<User> findByEmailAddress(String emailAddress);
    }


