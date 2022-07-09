package ru.job4j.filesearch;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {

    private PathMatcher pathMatcher;
    private Pattern pattern;
    private List<Path> matchedPaths = new ArrayList<>();
    private boolean regex = false;

    SearchFiles(String findPattern, String type) {
        if ("regex".equals(type)) {
            regex = true;
            pattern = Pattern.compile(findPattern);
        } else {
            pathMatcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + findPattern);
        }
    }

    /* Compares the glob pattern against
      the file or directory name.*/
    void match(Path file) {
        Path name = file.getFileName();

        if (regex) {
            if (name != null) {
                /*get a matcher object*/
                Matcher matcher = pattern.matcher(name.toString());
                if (matcher.find()) {
                    matchedPaths.add(file);
                }
            }
        } else {
            if (name != null && pathMatcher.matches(name)) {
                matchedPaths.add(file);
            }
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        match(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        match(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public List<Path> getMatchedPaths() {
        return matchedPaths;
    }
}
