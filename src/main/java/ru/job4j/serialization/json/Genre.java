package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Genre {
    @XmlAttribute
    private String genre;

    public Genre() {

    }

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
