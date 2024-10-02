package labs.lab2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Interface1 ser = new XMLSerializer();
//        XmlMapper mapper = new XmlMapper();
//        mapper.registerModule(new JavaTimeModule());
        Person p = new Person.PersonBuilder().firstName("FN").lastName("LK").birthDate(LocalDate.of(1990, 1, 1)).build();
        ser.toFile("kksk", p);
//        ser = new YANLSerializer();
//        String s = null;
//        try {
//            mapper.writeValue(new File("1.xml"), p);
////            System.out.println(s);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
        //Person p = new Person.PersonBuilder().firstName("FN").lastName("LK").birthDate(LocalDate.of(1990, 1, 1)).build();
//        String s = null;
//        try {
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("1.json"), p);
//            System.out.println(s);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Person p = mapper.readValue(new File("1.json"), Person.class);
//        System.out.println(p);

//
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        mapper.registerModule(new JavaTimeModule());
//        Flat fl = new Flat.FlatBuilder("3456").owner(null).build();
//        Person p = new Person.PersonBuilder().firstName("FN").lastName("LK").birthDate(LocalDate.of(1990, 1, 1))
//                .flats(List.of(fl)).build();
////        String s = null;
//        try {
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("1.yaml"), p);
////            System.out.println(s);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
