package com.example.test.service;

import com.example.test.common.exception.ServiceException;
import com.example.test.dao.UserDao;
import com.example.test.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder encoder;

    public User getById(Long id) {
        log.trace("Getting a user with id {}.", id);
        User user = userDao.getById(id).orElseThrow(() -> new ServiceException("There is no user with id {}.", id));
        log.trace("Getting a user with id {} has completed successfully.", id);
        return user;
    }

    public User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDao.getByLogin(user.getLogin()).orElseThrow(() -> new ServiceException("Current user cannot be identified."));
    }

    public User getByLogin(String login) {
        log.trace("Getting a user with login {}.", login);
        User user = userDao.getByLogin(login).orElseThrow(() -> new ServiceException("There is no user with login {}.", login));
        log.trace("Getting a user with login {} has completed successfully.", login);
        return user;
    }

    public User create(User user) {
        log.trace("Creating a user with login {}.", user.getLogin());
        user.setPassword(encoder.encode(user.getPassword()));
        user = userDao.createOrUpdate(user);
        log.trace("Creating a user with login {} has completed successfully.", user.getLogin());
        return user;
    }

    public User update(User user) {
        log.trace("Updating a user with login {}.", user.getLogin());
        if (encoder.matches(user.getPassword(), getById(user.getId()).getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user = userDao.createOrUpdate(user);
        log.trace("Updating a user with login {} has completed successfully.", user.getLogin());
        return user;
    }

}
