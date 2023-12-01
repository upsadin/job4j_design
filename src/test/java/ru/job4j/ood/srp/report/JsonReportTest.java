package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.CalendarJsonAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

@Disabled
class JsonReportTest {
    @Test
    public void whenGenerateThenGetReport() {
        Employee em1 = new Employee(
                "Pavel",
                new GregorianCalendar(2022, Calendar.JANUARY, 12),
                new GregorianCalendar(2023, Calendar.JANUARY, 12),
                72000.0
        );
        Employee em2 = new Employee(
                "Viktor",
                new GregorianCalendar(2020, Calendar.JUNE, 05),
                new GregorianCalendar(2023, Calendar.NOVEMBER, 30),
                45000.0
        );
        Store store = new MemStore();
        store.add(em1);
        store.add(em2);
        CalendarJsonAdapter calendarJsonAdapter = new CalendarJsonAdapter();
        Report report = new JsonReport(store, calendarJsonAdapter);
        String expected = """
                        [
                          {
                            "name": "Pavel",
                            "hired": "12:01:2022 00:00",
                            "fired": "12:01:2023 00:00",
                            "salary": 72000.0
                          },
                          {
                            "name": "Viktor",
                            "hired": "05:06:2020 00:00",
                            "fired": "30:11:2023 00:00",
                            "salary": 45000.0
                          }
                        ]""";
        String rsl = report.generate(em -> em.getSalary() > 1000);
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    public void whenGenerateAndFindNothing() {
        Employee em1 = new Employee(
                "Pavel",
                new GregorianCalendar(2022, Calendar.JANUARY, 12),
                new GregorianCalendar(2023, Calendar.JANUARY, 12),
                100000.00
        );
        Store store = new MemStore();
        store.add(em1);
        CalendarJsonAdapter calendarJsonAdapter = new CalendarJsonAdapter();
        Report report = new JsonReport(store, calendarJsonAdapter);
        assertThatThrownBy(() -> report.generate(em -> em.getSalary() > 120000.00))
                .hasMessage("Nothing found");
    }
}