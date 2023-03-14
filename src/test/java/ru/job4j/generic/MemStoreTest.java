package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("1", new User("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("10", new User("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }

    @Test
    void whenAddAndFindThenRoleNameIsPavel() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Pavel");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsPavel() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        store.add(new User("1", "PavelII"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Pavel");
    }

    @Test
    void whenReplaceThenRolenameIsPavelII() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        store.replace("1", new User("1", "PavelII"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("PavelII");
    }

    @Test
    void whenNoReplaceUserThenNoChangeRolename() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        store.replace("10", new User("10", "PavelII"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Pavel");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsPavel() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Pavel");
    }

    @Test
    void whenReplaceRoleOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        boolean rsl = store.replace("1", new User("1", "PavelII"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceRoleNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        boolean rsl = store.replace("10", new User("10", "PavelII"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteRoleOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteRoleNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Pavel"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}