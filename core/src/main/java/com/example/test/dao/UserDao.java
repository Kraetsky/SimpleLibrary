package com.example.test.dao;

import com.example.test.domain.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final SessionFactory sessionFactory;

    public Optional<User> getById(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from users where id = :id")
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    public Optional<User> getByLogin(String login) {
        return sessionFactory.getCurrentSession()
                .createQuery("from users where login = :login")
                .setParameter("login", login)
                .uniqueResultOptional();
    }

    public User createOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

}
