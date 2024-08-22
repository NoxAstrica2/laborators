package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class FlatTest {
    FlatType ft;
    Person person;
    Flat flat;


    @BeforeEach
    void beforeEach() {
        ft = new FlatType("title", 3, 34.8, null);
        person = new Person.PersonBuilder().firstName("fn").lastName("ln").PIN("PIN").build();
        flat = new Flat.FlatBuilder("1-A").type(ft).owner(person).build();

    }

    @Test
    void stringRepresentationTest() {
        String expected = "Flat{type=FlatType[title=title, roomAmount=3, area=34.8, planUrl=null], number=1-A, owner=Person{firstName=fn, lastName=ln, PIN=PIN, birthDate=null, notes=null}}";
        String actual = flat.toString();
        assertEquals(expected, actual);
    }

    @Test
    void notEqualsTest(){
        Flat flat2 = new Flat.FlatBuilder("1-B").type(ft).owner(person).build();
        assertNotEquals(flat2, flat);
    }

}