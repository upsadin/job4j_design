package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(5, 10);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void isThisUnknownBecauseEdge() {
        Box box = new Box(4, -2);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void fourVertices() {
        Box box = new Box(4, 4);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void notVertices() {
        Box box = new Box(120, 4);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isNegative();
    }

    @Test
    void whenExist() {
        Box box = new Box(8, 6);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenDontExist() {
        Box box = new Box(9, 6);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenAreatwentyFour() {
        Box box = new Box(8, 2);
        assertThat(box.getArea()).isCloseTo(24d, Percentage.withPercentage(1.0d));
    }

    @Test
    void whenAreazero() {
        Box box = new Box(15, 2);
        assertThat(box.getArea()).isZero();
    }
}