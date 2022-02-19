package ru.job4j.collection.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted;
        Map<Integer, String> map = new HashMap<>();
        for (User usr : previous) {
            map.put(usr.getId(), usr.getName());
        }
        for (User usr : current) {
            String oldValue = map.put(usr.getId(), usr.getName());
            if (oldValue == null) {
                added++;
            } else if (!usr.getName().equals(oldValue)) {
                changed++;
            }
        }
        deleted = Math.abs(map.size() - current.size());
        return new Info(added, changed, deleted);
    }

}