package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int pageCount;
    @XmlAttribute
    private boolean read;
    private Publisher publisher;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private String[] genres;

    public Book() {
    }

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

    public static void main(String[] args) throws Exception {

        final Book book = new Book("Alice's Adventures in Wonderland", 96, false, new Publisher("АСТ"),
                new String[]{"Fiction", "Fantasy"});

        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(book, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        }
    }

}

