package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Set<String> val = read.lines()
                    .filter(line -> !"".equals(line.trim()) && !line.startsWith("#"))
                    .collect(Collectors.toSet());
            for (String str : val) {
                String[] strVal = str.split("=");
                checkLine(strVal);
                values.put(strVal[0], strVal[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkLine(String[] line) throws IllegalArgumentException {
        if (line.length < 2 || "".equals(line[0].trim())) {
            throw new IllegalArgumentException();
        }
    }

    public String value(String key) {
        return values.get(key);
    }


    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("app.properties");
        System.out.println(conf);
        conf.load();
        System.out.println(conf.value("hibernate.connection.url"));
    }
}