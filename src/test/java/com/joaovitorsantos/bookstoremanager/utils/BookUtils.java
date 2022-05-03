package com.joaovitorsantos.bookstoremanager.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.joaovitorsantos.bookstoremanager.dto.BookDTO;
import com.joaovitorsantos.bookstoremanager.entity.Book;

import static com.joaovitorsantos.bookstoremanager.utils.AuthorUtils.creatFakerAuthor;
import static com.joaovitorsantos.bookstoremanager.utils.AuthorUtils.createFakerAuthorDTO1;

public class BookUtils {
    private static final Faker faker = Faker.instance();

    public static BookDTO createFakerBookDTO() {

        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 200))
                .isbn(String.valueOf("0596520689"))
                .publisherName(faker.book().publisher())
                .author(createFakerAuthorDTO1())
                .build();
    }
    public static Book creatFakerBook() {
        return Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 200))
                .isbn(Integer.valueOf("0-596-52068-9"))
                .publisherName(faker.book().publisher())
                .author(creatFakerAuthor())
                .build();
    }
    public static String asJsonString(BookDTO bookDTO) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(bookDTO);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
