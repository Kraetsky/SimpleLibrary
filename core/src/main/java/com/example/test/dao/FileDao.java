package com.example.test.dao;

import com.example.test.domain.File;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileDao {

    private final SessionFactory sessionFactory;

    public File save(File file) {
        sessionFactory.getCurrentSession().save(file);
        return file;
    }

    public Optional<File> getByUid(String uid) {
        return sessionFactory.getCurrentSession()
                .createQuery(" from files where uid = :uid")
                .setParameter("uid", uid)
                .uniqueResultOptional();
    }

}
