package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

@Disabled
class XmlReportTest {
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

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report report = new XmlReport(store, parser);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Pavel</name>
                        <hired>12:01:2022 00:00</hired>
                        <fired>12:01:2023 00:00</fired>
                        <salary>72000.0</salary>
                    </employee>
                    <employee>
                        <name>Viktor</name>
                        <hired>05:06:2020 00:00</hired>
                        <fired>30:11:2023 00:00</fired>
                        <salary>45000.0</salary>
                    </employee>
                </employees>
                """;
        String rsl = report.generate(em -> em.getSalary() > 1000);
        assertThat(rsl).isEqualTo(expected);
    }
}