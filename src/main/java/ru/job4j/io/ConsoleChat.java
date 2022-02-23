package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String input = "", command = "", answer;
        Random rand = new Random();
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        if (phrases.size() == 0) {
            System.out.println("Файл " + botAnswers + " не содержит фраз.");
            return;
        }
        System.out.println("Добро пожаловать. Отправь сообщение для начала общения." + System.lineSeparator()
                + "Доступны команды: стоп, закончить, продолжить.");
        Scanner in = new Scanner(System.in);
        while (!input.equals(STOP)) {
            input = in.nextLine();
            log.add(input + System.lineSeparator());
            if (CONTINUE.equals(input) || (!OUT.equals(input) && !STOP.equals(input)) && !command.equals(OUT)) {
                answer = phrases.get(rand.nextInt(0, phrases.size()));
                log.add(answer + System.lineSeparator());
                System.out.println(answer);
            }
            if (OUT.equals(input) || CONTINUE.equals(input)) {
                command = input;
            }
        }
        in.close();
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            log.forEach(out::write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat_log.txt", "./data/log1.csv");
        cc.run();
    }
}