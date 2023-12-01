package ru.job4j.ood.srp.formatter;

import com.google.gson.*;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarJsonAdapter implements JsonSerializer<Calendar> {
    private static final DateTimeParser DATE_TIME_PARSER = new ReportDateTimeParser();

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(DATE_TIME_PARSER.parse(calendar));
    }
}
