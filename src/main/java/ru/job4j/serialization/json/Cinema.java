package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "cinema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cinema {
    @XmlAttribute
    private boolean released;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private  int budget;
    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    private String[] actors;
    private Genre genre;

    public Cinema() {

    }

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
