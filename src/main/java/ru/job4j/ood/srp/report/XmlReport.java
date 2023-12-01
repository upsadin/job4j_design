package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XmlReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(XMLEmployees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new XMLEmployees(store.findBy(filter), dateTimeParser), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employee")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class XMLEmployee {
        private String name;
        private String hired;
        private String fired;
        private double salary;

        public XMLEmployee(Employee employee, DateTimeParser parser) {
            name = employee.getName();
            hired = parser.parse(employee.getHired());
            fired = parser.parse(employee.getFired());
            salary = employee.getSalary();
        }

        public XMLEmployee() {

        }
    }

    @XmlRootElement(name = "employees")
    public static class XMLEmployees {

        @XmlElement(name = "employee")
        private List<XMLEmployee> xmlEmployees = new ArrayList<>();

        public XMLEmployees(List<Employee> employees, DateTimeParser parse) {
            for (Employee em : employees) {
                this.xmlEmployees.add(new XMLEmployee(em, parse));
            }
        }

        public XMLEmployees() {

        }
    }
}
