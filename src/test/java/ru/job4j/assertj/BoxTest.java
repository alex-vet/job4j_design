package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .doesNotContainAnyWhitespaces();
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isNotZero()
                .isNotNegative()
                .isEqualTo(4);
    }

    @Test
    void isNotExist() {
        Box box = new Box(-1, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isEqualTo(false);
    }

    @Test
    void getArea() {
        Box box = new Box(4, 10);
        double number = box.getArea();
        assertThat(number).isNotZero()
                .isNotNegative()
                .isEqualTo(173.2D, withPrecision(0.05D));
    }
}