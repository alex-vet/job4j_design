package ru.job4j.serialization.xml;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Bookshelf {
    public static void main(String[] args) {
        final Book book = new Book("Alice's Adventures in Wonderland", 96, false, new Publisher("АСТ"),
                new String[]{"Fiction", "Fantasy"});
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/book.xml")
                ))) {
            out.println(book.toXMLString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(book.toXMLString());
    }
}
