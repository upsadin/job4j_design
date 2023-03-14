package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Pavel");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.add(new Role("1", "PavelII"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Pavel");
    }

    @Test
    void whenReplaceThenRolenameIsPavelII() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.replace("1", new Role("1", "PavelII"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("PavelII");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.replace("10", new Role("10", "PavelII"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Pavel");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Pavel");
    }

    @Test
    void whenReplaceRoleOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        boolean rsl = store.replace("1", new Role("1", "PavelII"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceRoleNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        boolean rsl = store.replace("10", new Role("10", "PavelII"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteRoleOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteRoleNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}