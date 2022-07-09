package ru.job4j.serialization.gson.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Bookshelf {
    public static void main(String[] args) {
        final Book book = new Book("Alice's Adventures in Wonderland", 96, false, new Publisher("АСТ"),
                new String[]{"Fiction", "Fantasy"});

        /* Преобразуем объект book в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));

        /* Модифицируем json-строку */
        final String bookJson =
                "{"
                        + "\"name\":\"Alice\\u0027s Adventures in Wonderland\","
                        + "\"pageCount\":96,"
                        + "\"read\":false,"
                        + "\"publisher\":"
                        + "{"
                        + "\"name\":\"АСТ\""
                        + "},"
                        + "\"genres\":"
                        + "[\"Fiction\",\"Fantasy\"]"
                        + "}";
        final Book bookMod = gson.fromJson(bookJson, Book.class);
        System.out.println(bookMod);
    }
}
