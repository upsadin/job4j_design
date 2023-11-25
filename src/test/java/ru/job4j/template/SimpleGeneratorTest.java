package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class SimpleGeneratorTest {
    @Test
    public void whenProduceTemplateWithValidKeys() {
        SimpleGenerator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Pavel");
        map.put("subject", "you");
        String rsl = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertThat(rsl).isEqualTo("I am Pavel, Who are you?");
    }

    @Test
    public void whenProduceTemplateWithInvalidKeys() {
        SimpleGenerator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("surnname", "Pavel");
        map.put("subject", "you");
        String rsl = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertThatThrownBy(() -> generator.produce("I am a ${name}, Who are ${subject}?", map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("неверный ключ \"name\"");
    }

    @Test
    public void whenProduceTemplateWithOddKey() {
        SimpleGenerator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Pavel");
        map.put("surnname", "Pavlov");
        map.put("subject", "you");
        String rsl = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertThatThrownBy(() -> generator.produce("I am a ${name}, Who are ${subject}?", map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Передан лишний ключ \"surnname\"");
    }

    @Test
    public void whenKeysDontExist() {
        SimpleGenerator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        String rsl = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertThatThrownBy(() -> generator.produce("I am a ${name}, Who are ${subject}?", map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("отсутствует ключ \"name\", \"subject\"");
    }

}