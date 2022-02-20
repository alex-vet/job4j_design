package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        String start = "", end = "";
        boolean prevStatus = false, currStatus;
        List<String> results = new ArrayList<>();
        List<String> data = load(source);
        if (data != null) {
            for (String val : data) {
                String[] el = val.split(" ");
                if (el.length == 2) {
                    currStatus = getStatus(el[0]);
                    if (prevStatus != currStatus) {
                        if (currStatus) {
                            start = el[1];
                        } else {
                            end = el[1];
                        }
                    }
                    prevStatus = currStatus;
                }
                if (!"".equals(start) && !"".equals(end)) {
                    results.add(start + ";" + end + ";");
                    start = "";
                    end = "";
                }
            }
        }
        save(results, target);
    }

    public List<String> load(String source) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            return in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void save(List<String> data, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            data.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean getStatus(String status) {
        boolean result;
        switch (status) {
            case ("400"):
            case ("500"):
                result = true;
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}