package com.gtsasil.library.api.model.repository;

import com.gtsasil.library.api.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByIsbn(String isbn);
}
