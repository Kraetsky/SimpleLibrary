package com.example.test.service;

import com.example.test.common.exception.ServiceException;
import com.example.test.dao.UserDao;
import com.example.test.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDao userDao;

    public User getByLogin(String login) {
        log.trace("Getting a user with login {}.", login);
        User user = userDao.getByLogin(login).orElseThrow(() -> new ServiceException("There is no user with login {}.", login));
        log.trace("Getting a user with login {} has completed successfully.", login);
        return user;
    }

}
