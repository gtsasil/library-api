package com.gtsasil.library.api.resource;

import com.gtsasil.library.api.dto.BookDTO;
import com.gtsasil.library.api.exception.ApiErrors;
import com.gtsasil.library.api.exception.BusinessException;
import com.gtsasil.library.api.model.entity.Book;
import com.gtsasil.library.api.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper mapper) {
        this.service = service;
        this.modelMapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody @Valid BookDTO dto){

        //depois de adicionar ModelMapper
        Book entity = modelMapper.map(dto, Book.class);

       /* Antes do adicionar a Library ModelMapper
        Book entity = Book.builder().author(dto.getAuthor()).title(dto.getTitle()).isbn(dto.getIsbn()).build();
        */

        entity = service.save(entity);

        /* Antes do adicionar a Library ModelMapper
        return BookDTO.builder().id(entity.getId()).author(dto.getAuthor()).title(dto.getTitle()).isbn(dto.getIsbn()).build();
        */

        //depois de adicionar ModelMapper
        return modelMapper.map(entity, BookDTO.class);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();

        return new ApiErrors(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerBusinessException(BusinessException ex){
        return new ApiErrors(ex);
    }
}
