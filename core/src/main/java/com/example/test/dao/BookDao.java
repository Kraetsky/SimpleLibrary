package com.example.test.dao;

import com.example.test.domain.Book;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDao {

    private final SessionFactory sessionFactory;

    public List<Book> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from books")
                .getResultList();
    }

    public Optional<Book> getById(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from books where id = :bookId")
                .setParameter("bookId", id)
                .uniqueResultOptional();
    }

    public Book saveOrUpdate(Book book) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(book);
        return book;
    }

    public void delete(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from books where id = :bookId")
                .setParameter("bookId", id)
                .executeUpdate();
    }

}
