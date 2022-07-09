package ru.job4j.filesearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        validateArgs(args);
        values = Arrays.stream(args)
                .map(el -> el.substring(1).split("=", 2))
                .collect(Collectors.toMap(k -> k[0], v -> v[1]));
    }

    private void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Wrong args");
        }
        for (String arg : args) {
            if (arg.indexOf("-") != 0) {
                throw new IllegalArgumentException("Args must start from -");
            }
            String[] el = arg.substring(1).split("=", 2);
            if (el.length != 2 || "".equals(el[0]) || "".equals(el[1])) {
                throw new IllegalArgumentException("Args must be like '-SOME_NAME=SOME_VALUE'");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}