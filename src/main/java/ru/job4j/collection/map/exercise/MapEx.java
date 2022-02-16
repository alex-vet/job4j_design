package ru.job4j.collection.map.exercise;

import java.util.*;

public class MapEx {
    public static void main(String[] args) {
        User usr1 = new User("User1", 0, new GregorianCalendar(1990, Calendar.JANUARY, 15, 12, 1, 10));
        User usr2 = new User("User1", 0, new GregorianCalendar(1990, Calendar.JANUARY, 15, 12, 1, 10));
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(usr1, new Object());
        userMap.put(usr2, new Object());
        userMap.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
    }
}
