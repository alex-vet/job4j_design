package ru.job4j.filesearch;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Find {
    String dir, type, out, name;

    public static void main(String[] args) throws IOException {
        Find find = new Find();
        find.validateArgs(args);
        List<Path> listFiles = find.search(Paths.get(find.dir), find.name);
        listFiles.forEach(System.out::println);
        File dir = new File(find.out);
        if (dir.getParentFile() == null) {
            dir = new File(".\\" + find.out);
        }
        dir.getParentFile().mkdir();
        try (PrintWriter out = new PrintWriter(new FileWriter(dir.getAbsoluteFile()))) {
            for (Path path : listFiles) {
                out.write(path.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Path> search(Path root, String condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition, type);
        Files.walkFileTree(root, searcher);
        return searcher.getMatchedPaths();
    }


    private void validateArgs(String[] args) {
        String errArgs = "Wrong args. Usage: java -jar find.jar -d=c:/ -n=*.?xt -t=mask||name||regex -o=LOG_FILE.txt";
        if (args.length != 4) {
            throw new IllegalArgumentException(errArgs);
        }
        ru.job4j.io.ArgsName arg = ArgsName.of(args);
        /*-d - директория, в которой начинать поиск.
        -n - имя файла, маска, либо регулярное выражение.
        -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
        -o - результат записать в файл.*/
        dir = arg.get("d");
        name = arg.get("n");
        type = arg.get("t");
        out = arg.get("o");
        if (dir == null || type == null || out == null || name == null) {
            throw new IllegalArgumentException(errArgs);
        }
        Path rootDir = Paths.get(dir);
        if (!rootDir.toFile().exists() || !rootDir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Not valid arguments. Root directory is not valid.");
        }
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Not valid arguments. Type is not valid."
                    + "Usage: -t=mask or -t=name or -t=regex");
        }
    }
}