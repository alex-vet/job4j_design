package ru.job4j.io;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenTwoPeriods() {
        Analizy log = new Analizy();
        log.unavailable("./data/log1.csv", "./data/unavailable.csv");
        List<String> out = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        assertThat(log.load("./data/unavailable.csv"), is(out));
    }

    @Test
    public void whenOnlyOnePeriod() {
        Analizy log = new Analizy();
        log.unavailable("./data/log2.csv", "./data/unavailable.csv");
        List<String> out = List.of("10:57:01;11:02:02;");
        assertThat(log.load("./data/unavailable.csv"), is(out));
    }

    @Test
    public void whenZeroPeriods() {
        Analizy log = new Analizy();
        log.unavailable("./data/log3.csv", "./data/unavailable.csv");
        assertThat(log.load("./data/unavailable.csv"), is(Collections.emptyList()));
    }
}