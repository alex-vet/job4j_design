package ru.job4j.serialization.xml;

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

    public String toXMLString() {
        return "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>" + System.lineSeparator()
                + "<book name=\"" + name + "\" pageCount=\"" + pageCount + "\""
                + " read=\"" + read + "\">" + System.lineSeparator()
                + "    " + publisher.toXMLString() + System.lineSeparator()
                + genresToXMLString() + System.lineSeparator()
                + "</book>";
    }

    private String genresToXMLString() {
        StringBuilder xmlGenres = new StringBuilder();
        xmlGenres.append("    <genres>").append(System.lineSeparator());
        for (String genre : genres) {
            xmlGenres.append("        <genre>").append(genre).append("</genre>").append(System.lineSeparator());
        }
        xmlGenres.append("    </genres>");
        return xmlGenres.toString();
    }

    //<?xml version="1.1" encoding="UTF-8" ?>
    //<person sex="false" age="30">
    //    <contact phone="11-111"/>
    //    <statuses>
    //        <status>Worker</status>
    //        <status>Married</status>
    //    </statuses>
    //</person>
}

