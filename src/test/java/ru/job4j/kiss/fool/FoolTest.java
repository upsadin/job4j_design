package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenStartAtEqualsFizzBuzz() {
        Fool.setStartAt(15);
        assertThat(Fool.comp()).isEqualTo("FizzBuzz");
    }

    @Test
    void whenStartAtEqualsFizz() {
        Fool.setStartAt(18);
        assertThat(Fool.comp()).isEqualTo("Fizz");
    }

    @Test
    void whenStartAtEqualsBuzz() {
        Fool.setStartAt(25);
        assertThat(Fool.comp()).isEqualTo("Buzz");
    }

    @Test
    void whenStartAtEquals53() {
        Fool.setStartAt(53);
        assertThat(Fool.comp()).isEqualTo("53");
    }
}