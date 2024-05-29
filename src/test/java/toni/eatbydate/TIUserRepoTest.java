package toni.eatbydate;



import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import toni.eatbydate.entity.Product;
import toni.eatbydate.entity.User;
import toni.eatbydate.repository.ProductRepo;
import toni.eatbydate.repository.UserRepo;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;
@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class TIUserRepoTest {

        @Autowired
        private UserRepo userRepository;

        @Test
        public void testCreateUser() {
            User user = new User("testuser", "password123", "testuser@example.com");

            User savedUser = userRepository.save(user);

            assertThat(savedUser).isNotNull();
            assertThat(savedUser.getUserId()).isNotNull();
        }

        @Test
        public void testFindUserById() {
            User user = new User("testuser", "password123", "testuser@example.com");

            User savedUser = userRepository.save(user);
            Optional<User> foundUser = userRepository.findById(savedUser.getUserId());

            assertThat(foundUser).isPresent();
            assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        }

        @Test
        public void testUpdateUser() {
            User user = new User("testuser", "password123", "testuser@example.com");

            User savedUser = userRepository.save(user);
            savedUser.setEmailAddress("updated@example.com");
            User updatedUser = userRepository.save(savedUser);

            assertThat(updatedUser.getEmailAddress()).isEqualTo("updated@example.com");
        }

        @Test
        public void testDeleteUser() {
            User user = new User("testuser", "password123", "testuser@example.com");

            User savedUser = userRepository.save(user);
            userRepository.deleteById(savedUser.getUserId());
            Optional<User> foundUser = userRepository.findById(savedUser.getUserId());

            assertThat(foundUser).isNotPresent();
        }
    }
