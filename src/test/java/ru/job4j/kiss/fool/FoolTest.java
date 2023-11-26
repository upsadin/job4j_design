package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenStartAtEqualsFizzBuzz() {
        int startAt = 15;
        assertThat(Fool.comp(startAt)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenStartAtEqualsFizz() {
        int startAt = 18;
        assertThat(Fool.comp(startAt)).isEqualTo("Fizz");
    }

    @Test
    void whenStartAtEqualsBuzz() {
        int startAt = 25;
        assertThat(Fool.comp(startAt)).isEqualTo("Buzz");
    }

    @Test
    void whenStartAtEquals53() {
        int startAt = 53;
        assertThat(Fool.comp(startAt)).isEqualTo("53");
    }
}