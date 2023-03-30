package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, i -> i == 3);
        assertThat(input).containsExactly(1);
    }

    @Test
    void whenDontRemoveIf() {
        ListUtils.removeIf(input, i -> i > 3);
        assertThat(input).containsExactly(1, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, i -> i == 3, 555);
        assertThat(input).contains(555);
    }

    @Test
    void whenDoesntReplaceIf() {
        ListUtils.replaceIf(input, i -> i == 2, 555);
        assertThat(input).doesNotContain(555);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAllOneElement() {
        ListUtils.removeAll(input, Arrays.asList(1, 2));
        assertThat(input).containsOnly(3);
    }
}