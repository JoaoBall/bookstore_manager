package com.joaovitorsantos.bookstoremanager.service;

import com.joaovitorsantos.bookstoremanager.dto.MessageResponseDTO;
import com.joaovitorsantos.bookstoremanager.entity.Book;
import com.joaovitorsantos.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(Book book) {
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Book created with ID" + savedBook.getId())
                .build();
    }
}
