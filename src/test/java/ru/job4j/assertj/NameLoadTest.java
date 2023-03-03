package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("123=qwe", "123qwer"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("123qwer");
    }

    @Test
    void checkKey() {
        NameLoad nm = new NameLoad();
        assertThatThrownBy(() -> nm.parse("=eee", "1=1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=eee");
    }

    @Test
    void checkValue() {
        NameLoad nm = new NameLoad();
        assertThatThrownBy(() -> nm.parse("1=1", "r=", "qqq=q"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("r=");
    }
}