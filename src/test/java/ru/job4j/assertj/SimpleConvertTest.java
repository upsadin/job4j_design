package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert sc = new SimpleConvert();
        String[] array = sc.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert sc = new SimpleConvert();
        List<String> list = sc.toList("1", "2", "3", "4", "5");
        assertThat(list).hasSize(5)
                .startsWith("1")
                .containsExactly("1", "2", "3", "4", "5")
                .containsAnyOf("11", "21", "1")
                .allSatisfy(e -> assertThat(e).isNotEmpty())
                .anyMatch(s -> s.equals("3"))
                .anySatisfy(s -> {
                    assertThat(s).isNotEmpty();
                    assertThat(s).isGreaterThan("0");
                });
    }

    @Test
    void checkSet() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> set = sc.toSet("first", "second", "first", "3", "31");
        assertThat(set).hasSize(4)
                .allSatisfy(s -> {
                    assertThat(s).isLowerCase();
                    assertThat(s).isNotEmpty();
                })
                .filteredOn(s -> s.length() > 2)
                .filteredOnAssertions(s -> assertThat(s).isEqualTo("second"))
                .hasSize(1);

    }

    @Test
    void checkMap() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> map = sc.toMap("first", "second", "first", "3", "31");
        assertThat(map).hasSize(4)
                .containsKey("first")
                .containsValue(0)
                .doesNotContainKey("2")
                .doesNotContainValue(11)
                .containsEntry("3", 3);
    }
}