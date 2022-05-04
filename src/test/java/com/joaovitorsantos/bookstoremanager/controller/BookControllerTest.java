package com.joaovitorsantos.bookstoremanager.controller;

import com.joaovitorsantos.bookstoremanager.dto.BookDTO;
import com.joaovitorsantos.bookstoremanager.dto.MessageResponseDTO;
import com.joaovitorsantos.bookstoremanager.service.BookService;
import com.joaovitorsantos.bookstoremanager.utils.BookUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.joaovitorsantos.bookstoremanager.utils.BookUtils.createFakerBookDTO;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String BOOK_API_URL_PATH = "/api/v1/books";
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(((viewName, locale) -> new MappingJackson2JsonView()))
                .build();
    }

    @Test
    void tesWhenPOSTisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakerBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID" + bookDTO.getId())
                .build();
        Mockito.when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtils.asJsonString((BookDTO) bookDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }
    @Test
    void tesWhenPOSTwithInvalidISBNisCalledThenABadRequestShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakerBookDTO();
        bookDTO.setIsbn(String.valueOf("invalid isbn"));

        mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BookUtils.asJsonString(bookDTO)))
                        .andExpect(status().isOk());
    }

}