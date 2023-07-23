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

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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
