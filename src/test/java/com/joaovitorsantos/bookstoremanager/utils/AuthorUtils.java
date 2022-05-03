package com.joaovitorsantos.bookstoremanager.utils;

import com.github.javafaker.Faker;
import com.joaovitorsantos.bookstoremanager.dto.AuthorDTO1;
import com.joaovitorsantos.bookstoremanager.entity.Author;

public class AuthorUtils {
    private static final Faker faker = Faker.instance();
    public static  AuthorDTO1 createFakerAuthorDTO1() {

        return AuthorDTO1.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }
    public static Author creatFakerAuthor() {
        return Author.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }
}
