package toni.eatbydate.service;

import toni.eatbydate.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getByEmail(String email);
}
