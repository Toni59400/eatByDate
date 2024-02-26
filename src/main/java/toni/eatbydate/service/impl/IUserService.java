package toni.eatbydate.service.impl;

import org.springframework.stereotype.Service;
import toni.eatbydate.entity.User;
import toni.eatbydate.repository.UserRepo;
import toni.eatbydate.service.UserService;

import java.util.Optional;

@Service
public class IUserService implements UserService {

    private UserRepo repo;

    public IUserService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return this.repo.findByEmailAddress(email);
    }
}
