package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

@Disabled
class HrReportTest {

    @Test
    public void whenGenerateThenGetReport() {
        Employee em1 = new Employee(
                "Pavel",
                new GregorianCalendar(2022, Calendar.JANUARY, 12),
                new GregorianCalendar(2023, Calendar.JANUARY, 12),
                72000.00
        );
        Employee em2 = new Employee(
                "Viktor",
                new GregorianCalendar(2020, Calendar.JUNE, 05),
                new GregorianCalendar(2023, Calendar.NOVEMBER, 31),
                45000.00
        );
        Employee em3 = new Employee(
                "Stepan",
                new GregorianCalendar(2020, Calendar.JUNE, 05),
                new GregorianCalendar(2023, Calendar.NOVEMBER, 31),
                75000.00
        );
        Store store = new MemStore();
        store.add(em1);
        store.add(em2);
        store.add(em3);
        DateTimeParser<Calendar> timeParserparser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new HrReport(store, timeParserparser);
        String expected = """
                Name; Hired; Fired; Salary;
                Stepan 75000.0
                Pavel 72000.0
                Viktor 45000.0
                """.replaceAll("\n", System.lineSeparator());
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
        DateTimeParser<Calendar> timeParserparser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new HrReport(store, timeParserparser);
        assertThatThrownBy(() -> report.generate(em -> em.getSalary() > 120000.00))
                .hasMessage("Nothing found");
    }

/*    @Test
    public void whenAddNullObject() {
        Employee em1 = null;
        Store store = new MemStore();
        store.add(em1);
        DateTimeParser<Calendar> timeParserparser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new HrReport(store, timeParserparser);
        assertThatThrownBy(() -> report.generate(em -> true))
                .isInstanceOf(IllegalArgumentException.class);
    }*/
}