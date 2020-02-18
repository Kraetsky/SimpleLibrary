package com.example.test.endpoint;

import com.example.test.domain.Book;
import com.example.test.domain.api.Response;
import com.example.test.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookEndpoint {

    private final BookService bookService;

    @GetMapping("/all")
    public Response getAll() {
        return Response.from(() -> bookService.getAll());
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Long id) {
        return Response.from(() -> bookService.getById(id));
    }

    @PostMapping
    public Response saveBook(@RequestBody Book book) {
        return Response.from(() -> bookService.saveOrUpdate(book));
    }

    @PutMapping
    public Response updateBook(@RequestBody Book book) {
        return Response.from(() -> bookService.saveOrUpdate(book));
    }

    @DeleteMapping("/{id}")
    public Response deleteBook(@PathVariable Long id) {
        return Response.from(() -> bookService.delete(id));
    }
}
