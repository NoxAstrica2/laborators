package labs.lab6.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Person {

    private UUID id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message
            = "firstName must be between 1 and 50 characters")
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[A-Z]{2}\\d{8}$", message = "PIN should be in format: the first two letters uppercase, followed by eight digit")
    private String PIN;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent
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

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
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

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String PIN;
        private LocalDate birthDate;
        private String notes;
        private List<Flat> flats;

        PersonBuilder() {
        }

        public PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder PIN(String PIN) {
            this.PIN = PIN;
            return this;
        }

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd"
        )
        public PersonBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Person build() {
            var person = new Person(this.firstName, this.lastName, this.PIN, this.birthDate, this.notes, this.flats);
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Person>> violations = validator.validate(person);
                List<String> validationError = violations.stream().map
                        (v -> "validation error in " + v.getPropertyPath() + ", value `" + v.getInvalidValue() + "` should satisfy condition: " + v.getMessage()).toList();
                if (!violations.isEmpty()) {
                    throw new ValidationException(String.join("\n", validationError));
                }
                return person;
            }
        }
    }
}
