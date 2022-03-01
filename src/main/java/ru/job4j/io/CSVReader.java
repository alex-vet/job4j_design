package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        CSVReader csv = new CSVReader();
        csv.checkArgs(argsName);
        Path source = Paths.get(argsName.get("path"));
        String[] filter = argsName.get("filter").split(",");
        int[] index = new int[filter.length];
        StringJoiner sj = new StringJoiner("");
        try (var scanner = new Scanner(source)) {
            while (scanner.hasNext()) {
                String[] columnsHead = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < columnsHead.length; i++) {
                    String a = columnsHead[i];
                    for (int j = 0; j < filter.length; j++) {
                        if (a.equals(filter[j])) {
                            index[j] = i;
                        }
                    }
                }
                for (int j : index) {
                    if (j < index.length - 1) {
                        sj.add(columnsHead[j] + ";");
                    } else {
                        sj.add(columnsHead[j]);
                    }
                }
                sj.add(System.lineSeparator());
            }
        }
        csv.printResult(sj.toString(), argsName.get("out"));
    }

    private void checkArgs(ArgsName args) {
        Path source = Paths.get(args.get("path"));
        if (!source.toFile().exists() || !source.toFile().isFile()) {
            throw new IllegalArgumentException("Path not valid or not exist.");
        }
        if (args.get("delimiter") == null) {
            throw new IllegalArgumentException("Delimiter must be defined.");
        }
        if (!"stdout".equals(args.get("out"))) {
            Path target = Paths.get(args.get("out"));
            if (!target.toFile().isFile()) {
                throw new IllegalArgumentException("Argument -out not valid.");
            }
        }
    }

    private void printResult(String result, String target) {
        if ("stdout".equals(target)) {
            System.out.println(result);
        } else {
            try (PrintWriter out = new PrintWriter(new FileWriter(target))) {
                out.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
