package labs.lab2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Person implements Comparable<Person> {

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

    @Override
    public int compareTo(Person o) {
        return lastName.equals(o.lastName) ? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }
}
