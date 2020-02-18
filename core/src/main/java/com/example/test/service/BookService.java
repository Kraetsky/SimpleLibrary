package com.example.test.service;

import com.example.test.common.exception.ServiceException;
import com.example.test.dao.BookDao;
import com.example.test.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookDao bookDao;

    public List<Book> getAll() {
        log.trace("Getting all books.");
        return bookDao.getAll();
    }

    public Book getById(Long id) {
        log.trace("Getting a book with id {}.", id);
        Book book = bookDao.getById(id).orElseThrow(() -> new ServiceException("There is no book with id {}.", id));
        log.trace("Getting a book with id {} has completed successfully.", id);
        return book;
    }

    public Book saveOrUpdate(Book book) {
        log.trace(book.getId() == null ? "Saving a new book." : "Updating a book with id {}.", book.getId());
        book = bookDao.saveOrUpdate(book);
        log.trace("Saving or updating a book with id {} has completed successfully", book.getId());
        return book;
    }

    public void delete(Long id) {
        log.trace("Deleting a book with id {}.", id);
        bookDao.delete(id);
        log.trace("Deleting a book with id {}. has completed successfully", id);
    }
}
