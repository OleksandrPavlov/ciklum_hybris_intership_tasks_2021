package com.learning.pavlov.services.impl;

import com.learning.pavlov.dao.UserDao;
import com.learning.pavlov.jdbc.TransactionManager;
import com.learning.pavlov.models.User;
import com.learning.pavlov.services.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final UserDao userDao;
    private final TransactionManager transactionManager;

    public AccountServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public long registerUser(final User user) {
        return transactionManager.executeInTransaction(() -> userDao.add(user));
    }

    @Override
    public Optional<User> signUser(User user) {
        Optional<User> userFromDB = transactionManager.executeInTransaction(() -> userDao.findByLogin(user.getLogin()));
        return userFromDB.isPresent() && user.getPassword().equals(userFromDB.get().getPassword()) ? userFromDB : Optional.empty();
    }

}
