package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlToPojo {
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Cinema.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Cinema cinemaUnmarsh;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\projects\\job4j_design\\data\\cinema.xml"))) {
            cinemaUnmarsh = (Cinema) unmarshaller.unmarshal(reader);
            System.out.println(cinemaUnmarsh.toString());
        }
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(cinemaUnmarsh, writer);
            System.out.println(writer.toString());
        }
    }
}
