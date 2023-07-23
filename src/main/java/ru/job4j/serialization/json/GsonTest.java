package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;

public class GsonTest {

    public static void main(String[] args) {
        final Cinema cinema = new Cinema(true, new Genre("action"), "Die Hard",
                28000000, new String[]{"Bruce Willis", "Alan Rickman"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cinema));

        final String newCinema = "{"
                + "\"released\":true,"
                + "\"genre\":"
                + "{\"genre\":\"action\"},"
                + "\"name\":\"Die Hard\","
                + "\"budget\":28000000,"
                + "\"actors\":"
                + "[\"Bruce Willis\",\"Alan Rickman\"]"
                + "}";
        final Cinema cinemaFromJson = gson.fromJson(newCinema, Cinema.class);
        System.out.println(cinemaFromJson);
    }
}
