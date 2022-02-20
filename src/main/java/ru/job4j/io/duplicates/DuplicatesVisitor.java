package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
        if (files.containsKey(fileProp)) {
            List<Path> duplicates = files.get(fileProp);
            duplicates.add(file);
            files.put(fileProp, duplicates);
        } else {
            List<Path> lst = new ArrayList<>();
            lst.add(file);
            files.put(fileProp, lst);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        List<Path> duplicate = new ArrayList<>();
        files.values()
                .stream()
                .filter(el -> el.size() > 1)
                .forEach(duplicate::addAll);
        return duplicate;
    }
}
