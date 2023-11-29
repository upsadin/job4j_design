package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) Math.round(o2.getSalary()) - (int) Math.round(o1.getSalary());
    }
}
