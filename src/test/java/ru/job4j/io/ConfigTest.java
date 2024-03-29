package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("comm")).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Don't impl this method yet!");
    }

    @Test
    void whenIllegalArgumentException() {
        String path = "./data/wrong_pair.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Нарушен формат у строки =jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenNotValue() {
        String path = "./data/not_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Нарушен формат у строки hibernate.connection.driver_class=");
    }
}