package ru.job4j.serialization.gson.book;

import java.util.Arrays;

public class Book {
    private final String name;
    private final int pageCount;
    private final boolean read;
    private final Publisher publisher;
    private final String[] genres;

    public Book(String name, int pageCount, boolean read, Publisher publisher, String[] genres) {
        this.name = name;
        this.pageCount = pageCount;
        this.read = read;
        this.publisher = publisher;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isRead() {
        return read;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String[] getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name='" + name + '\''
                + ", pageCount=" + pageCount
                + ", read=" + read
                + ", publisher=" + publisher
                + ", genres=" + Arrays.toString(genres)
                + '}';
    }
}
