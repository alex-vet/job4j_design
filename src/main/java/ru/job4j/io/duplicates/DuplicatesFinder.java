package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Path start = Paths.get("./");
        Files.walkFileTree(start, visitor);
        List<Path> duplicates = visitor.getDuplicates();
        duplicates.forEach(System.out::println);
    }
}
