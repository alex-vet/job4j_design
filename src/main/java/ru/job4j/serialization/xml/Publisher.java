package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "publisher")
public class Publisher {

    @XmlAttribute
    private String name;

    public Publisher() {
    }

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
