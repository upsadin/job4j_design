package ru.job4j.serialization.json;

import java.util.Arrays;

public class Cinema {
    private final boolean released;
    private final String name;
    private final int budget;
    private final String[] actors;
    private final Genre genre;

    public Cinema(boolean released, Genre genre, String name, int budget, String[] actors) {
        this.released = released;
        this.genre = genre;
        this.name = name;
        this.budget = budget;
        this.actors = actors;

    }

    @Override
    public String toString() {
        return "Cinema{"
                + "released=" + released
                + ", genre=" + genre
                + ", name='" + name + '\''
                + ", budget=" + budget
                + ", actors=" + Arrays.toString(actors)
                + '}';
    }
}
