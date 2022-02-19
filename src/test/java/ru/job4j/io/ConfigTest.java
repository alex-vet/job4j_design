package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;

public class ConfigTest {
    @Test
    public void whenSomeKeysFromAppProperties() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alexey Vetoshkin"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndNullStrings() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"), is("postgres"));
        assertThat(config.value("password"), is("password"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairBadFormat() {
        String path = "./data/pair_with_bad_format.properties";
        Config config = new Config(path);
        config.load();
    }
}
