package ru.job4j.serialization.xml;

public class Publisher {
    private final String name;

    public Publisher(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{"
                + "name='" + name + '\''
                + '}';
    }

    public String toXMLString() {
        return "<publisher"
                + " name=\"" + name + "\""
                + "/>";
    }
}
