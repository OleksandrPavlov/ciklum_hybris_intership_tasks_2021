package com.learning.pavlov.services;

import com.learning.pavlov.models.User;

import java.util.Optional;

public interface AccountService {

    long registerUser(User user);

    Optional<User> signUser(User user);
}
