package toni.eatbydate.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import toni.eatbydate.entity.User;
import toni.eatbydate.repository.UserRepo;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepo userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = this.userRepository.findByEmailAddress(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(password, user.getPasswordHash());
        }
        return false;
    }

    public User createUser(String username, String password, String email) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword, email);
        return this.userRepository.save(user);
    }
}
