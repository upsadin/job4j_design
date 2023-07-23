package ru.job4j.serialization.json;

public class Genre {
    private final String genre;
    public Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{"
                + "genre='" + genre
                + "\'}";
    }
}
