package labs.lab5;

import labs.lab5.dao.DataSource;
import labs.lab5.dao.FlatDao;
import labs.lab5.dao.FlatTypeDao;
import labs.lab5.dao.PersonDao;
import labs.lab5.model.Flat;
import labs.lab5.model.FlatType;
import labs.lab5.model.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataSource.createTablesStructure();

        FlatType type = new FlatType("First three-rooms", 3, 45.3, null);
        FlatTypeDao fd = new FlatTypeDao();
        fd.add(type);

        type = new FlatType("Three", 3, 45.3, null);
        fd.add(type);
        System.out.println(fd.getByTitle("First"));
        fd.update(new FlatType(UUID.fromString("01928a6b-45e5-7a2f-8ecf-a713a5fc0a04"), "Other wo", 20, 0.0, null));
        System.out.println(fd.getByTitlePart("ee"));
        System.out.println(new FlatTypeDao().deleteById(UUID.fromString("01928a6b-45e5-7a2f-8ecf-a713a5fc0a04")));
        System.out.println(new FlatTypeDao().getAll());

        Person person = Person.builder()
                .firstName("Abs")
                .lastName("ALast_name")
                .PIN("AS67567870")
                .birthDate(LocalDate.now())
                .notes("rs.getString")
                .build();

        PersonDao pd = new PersonDao();
        pd.add(person);

        System.out.println(pd.getAll());

        Person p = new PersonDao().getAll().get(0);
        p.setFirstName("Otherк");
        pd.update(p);
        System.out.println(pd.getAll());
        System.out.println(pd.deleteById(UUID.fromString("01928a78-9862-7ba0-9ee1-975891685d43")));


        type = fd.getAll().get(0);
        Flat flat = new Flat.FlatBuilder("346")
                .owner(p)
                .type(type)
                .build();

        FlatDao flatDao = new FlatDao();
        flatDao.add(flat);
        System.out.println(flatDao.getAll());
    }
}
