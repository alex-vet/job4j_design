package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            StringBuilder builder = new StringBuilder();
            String prevValue = null;
            String line;
            while ((line = in.readLine()) != null) {
                String[] values = line.split(" ");
                if ("400".equals(values[0])
                        || "500".equals(values[0])) {
                    if (!"400".equals(prevValue) && !"500".equals(prevValue)) {
                        prevValue = values[0];
                        builder.append(values[1]).append(";");
                    }
                } else if (("400".equals(prevValue)) || "500".equals(prevValue)) {
                    prevValue = null;
                    builder.append(values[1]).append(";");
                    out.println(builder);
                    builder.delete(0, builder.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy log = new Analizy();
        log.unavailable("./data/log1.csv", "./data/unavailable.csv");
    }
}