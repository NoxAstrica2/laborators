package labs.lab2.service;


import labs.lab2.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class PersonService {

    private List<Person> persons;

    public Person byPIN(String pin) {
        return persons.stream().filter(p -> p.getPIN().equals(pin)).findFirst().orElseThrow(()->
                new RuntimeException("Person with PIN " + pin + "not found"));
    }

    public List<Person> containsPin(String pin){
        return persons.stream().filter(p -> p.getPIN().equalsIgnoreCase(pin)).toList();
    }

    public List<Person> sorted(){
        return persons.stream().sorted().toList();
    }

}
