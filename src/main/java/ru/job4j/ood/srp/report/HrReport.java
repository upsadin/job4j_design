package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class HrReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public HrReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> found = store.findBy(filter);
        if (found.size() == 0) {
            throw new IllegalArgumentException("Nothing found");
        }
        Collections.sort(found, new SalaryComparator());
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : found) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
