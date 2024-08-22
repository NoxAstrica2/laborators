package labs.lab1;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Person {

    private String firstName;
    private String lastName;
    private String PIN;
    private LocalDate birthDate;
    @EqualsAndHashCode.Exclude
    private String notes;
    @EqualsAndHashCode.Exclude
    private List<Flat> flats;

    private Person(String firstName, String lastName, String PIN, LocalDate birthDate, String notes, List<Flat> flats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PIN = PIN;
        this.birthDate = birthDate;
        this.notes = notes;
        this.flats = flats;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", PIN=" + PIN +
                ", birthDate=" + birthDate +
                ", notes=" + notes + '}';
    }

}
