package labs.lab3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private String PIN;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @EqualsAndHashCode.Exclude
    private String notes;
    @EqualsAndHashCode.Exclude
//    @JacksonXmlElementWrapper(useWrapping = false)
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
