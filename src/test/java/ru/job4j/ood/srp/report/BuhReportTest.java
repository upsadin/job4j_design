package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
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
class BuhReportTest {
    @Test
    public void whenGenerateAndGetReportInUSD() {
        Employee em1 = new Employee(
                "Pavel",
                new GregorianCalendar(2022, Calendar.JANUARY, 12),
                new GregorianCalendar(2023, Calendar.JANUARY, 12),
                100000.00
        );
        Employee em2 = new Employee(
                "Viktor",
                new GregorianCalendar(2020, Calendar.JUNE, 05),
                new GregorianCalendar(2023, Calendar.NOVEMBER, 30),
                45000.00
        );
        Employee em3 = new Employee(
                "Danil",
                new GregorianCalendar(2023, Calendar.JUNE, 02),
                new GregorianCalendar(2023, Calendar.DECEMBER, 15),
                35000.00
        );
        Store store = new MemStore();
        store.add(em1);
        store.add(em2);
        store.add(em3);
        DateTimeParser<Calendar> timeParserparser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Currency target = Currency.USD;
        Report report = new BuhReport(store, timeParserparser, Currency.RUB, Currency.USD, converter);
        String expected = """
                Name; Hired; Fired; Salary(%s);
                Pavel 12:01:2022 00:00 12:01:2023 00:00 1620.0
                Viktor 05:06:2020 00:00 30:11:2023 00:00 729.0
                """.replaceAll("\n", System.lineSeparator()).formatted(target);
        String rsl = report.generate(em -> em.getSalary() > 40000);
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
        Report report = new BuhReport(store, timeParserparser, Currency.RUB, Currency.USD, converter);
        assertThatThrownBy(() -> report.generate(em -> em.getSalary() > 150000))
                .hasMessage("Nothing found");
    }

}