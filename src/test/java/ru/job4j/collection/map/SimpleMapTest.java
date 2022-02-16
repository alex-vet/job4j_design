package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleMapTest {
    @Test
    public void thenAdd() {
        Map<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Test", 1));
        assertTrue(map.put("тест", 2));
    }

    @Test
    public void thenAddFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put(null, 0);
        assertFalse(map.put("test", 1));
        assertFalse(map.put(null, 0));
    }

    @Test
    public void thenExpand() {
        Map<String, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 11; i++) {
            assertTrue(map.put("test" + i, i));
        }
    }

    @Test
    public void thenGet() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put(null, 0);
        assertThat(map.get("test"), is(1));
        assertThat(map.get(null), is(0));
    }

    @Test
    public void thenIt() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put(null, 0);
        map.put("тест", 333);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void thenConcurrentModificationException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        Iterator<Integer> iterator = map.iterator();
        map.put(3, "test");
        iterator.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void thenNoSuchElementException() {
        Map<String, String> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        iterator.next();
    }

    @Test
    public void thenRem() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put(null, 0);
        assertTrue(map.remove("test"));
        assertTrue(map.remove(null));
        assertFalse(map.remove(null));
        assertFalse(map.remove("test"));
    }
}