package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonjavaUsage {
    public static void main(String[] args) {
        JSONObject jsonGenre = new JSONObject("{\"genre\":\"action\"}");

        /*List<String> list = new ArrayList<>();
        list.add("Bruce Willis");
        list.add("Alan Rickman");
        JSONArray jsonActors = new JSONArray(list);*/
        JSONArray jsonActors = new JSONArray(Arrays.asList(new String[]{"Bruce Willis", "Alan Rickman"}));

        Cinema cinema = new Cinema(true, new Genre("action"), "Die Hard",
                28000000, new String[]{"Bruce Willis", "Alan Rickman"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("released", cinema.isReleased());
        jsonObject.put("budget", cinema.getBudget());
        jsonObject.put("name", cinema.getName());
        jsonObject.put("actors", jsonActors);
        jsonObject.put("genre", jsonGenre);

        System.out.println("jsonObject " + jsonObject.toString());

        System.out.println(new JSONObject(cinema));
    }
}
