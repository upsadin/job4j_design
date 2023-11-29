package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class BuhReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency sourceCurrency;
    private final Currency targetCurrency;
    private final CurrencyConverter converter;

    public BuhReport(Store store,
                     DateTimeParser<Calendar> dateTimeParser,
                     Currency sourceCurrency,
                     Currency targetCurrency,
                     CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary(%s);".formatted(targetCurrency)).append(System.lineSeparator());
        List<Employee> found = store.findBy(filter);
        if (found.size() == 0) {
            throw new IllegalArgumentException("Nothing found");
        }
        for (Employee em : store.findBy(filter)) {
            text.append(
                            em.getName()).append(" ")
                    .append(dateTimeParser.parse(em.getHired())).append(" ")
                    .append(dateTimeParser.parse(em.getFired())).append(" ")
                    .append(converter.convert(sourceCurrency, em.getSalary(), targetCurrency))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
