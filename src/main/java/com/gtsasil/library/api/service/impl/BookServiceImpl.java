package com.gtsasil.library.api.service.impl;

import com.gtsasil.library.api.exception.BusinessException;
import com.gtsasil.library.api.model.entity.Book;
import com.gtsasil.library.api.model.repository.BookRepository;
import com.gtsasil.library.api.service.BookService;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if (repository.existsByIsbn(book.getIsbn())){
            throw  new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }
}
