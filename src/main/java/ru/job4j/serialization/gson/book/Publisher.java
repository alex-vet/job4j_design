package ru.job4j.serialization.gson.book;

public class Publisher {
    private final String name;

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Publisher{"
                + "name='" + name + '\''
                + '}';
    }
}
