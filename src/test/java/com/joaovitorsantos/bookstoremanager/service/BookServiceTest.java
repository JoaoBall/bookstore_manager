package com.joaovitorsantos.bookstoremanager.service;


import com.joaovitorsantos.bookstoremanager.dto.BookDTO;
import com.joaovitorsantos.bookstoremanager.entity.Book;
import com.joaovitorsantos.bookstoremanager.exception.BookNotFoundException;
import com.joaovitorsantos.bookstoremanager.repository.BookRepository;
import com.joaovitorsantos.bookstoremanager.utils.BookUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFundBook = BookUtils.creatFakerBook();

        Mockito.when(bookRepository.findById(expectedFundBook.getId())).thenReturn(Optional.of(expectedFundBook));

        BookDTO bookDTO = bookService.findById(expectedFundBook.getId());

        Assertions.assertEquals(expectedFundBook.getName(), bookDTO.getName());
        Assertions.assertEquals(expectedFundBook.getPublisherName(), bookDTO.getPublisherName());


    }
}
