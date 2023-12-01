package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.formatter.CalendarJsonAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final CalendarJsonAdapter calendarJsonAdapter;

    public JsonReport(Store store, CalendarJsonAdapter calendarJsonAdapter) {
        this.store = store;
        this.calendarJsonAdapter = calendarJsonAdapter;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, calendarJsonAdapter);
        builder.registerTypeAdapter(GregorianCalendar.class, calendarJsonAdapter);
        Gson gson = builder.setPrettyPrinting().create();
        List<Employee> found = store.findBy(filter);
        if (found.size() == 0) {
            throw new IllegalArgumentException("Nothing found");
        }
        return gson.toJson(found);
    }
}
