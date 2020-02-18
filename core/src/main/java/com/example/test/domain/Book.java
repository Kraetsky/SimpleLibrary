package com.example.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    @Column(name = "release_year")
    private Long releaseYear;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "number_of_pages")
    private Long numberOfPages;
    @Column(name = "is_available")
    private Boolean isAvailable;

}
