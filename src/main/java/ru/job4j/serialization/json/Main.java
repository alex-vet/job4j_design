package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.gson.book.Book;
import ru.job4j.serialization.gson.book.Publisher;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonPublisher = new JSONObject("{\"name\":\"АСТ\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Fiction");
        list.add("Fantasy");
        JSONArray jsonGenres = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Book book = new Book("Alice's Adventures in Wonderland", 96, false, new Publisher("АСТ"),
                new String[]{"Fiction", "Fantasy"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", book.getName());
        jsonObject.put("pageCount", book.getPageCount());
        jsonObject.put("read", book.isRead());
        jsonObject.put("publisher", jsonPublisher);
        jsonObject.put("genres", jsonGenres);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(book).toString());
    }

}
