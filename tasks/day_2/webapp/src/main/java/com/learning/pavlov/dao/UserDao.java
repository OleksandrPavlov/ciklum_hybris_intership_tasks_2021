package com.learning.pavlov.dao;

import com.learning.pavlov.models.User;

import java.util.Optional;

public interface UserDao {
    long add(User user);

    Optional<User> findByLogin(String login);
}
