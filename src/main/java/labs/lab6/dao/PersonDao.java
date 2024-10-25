package labs.lab6.dao;

import com.fasterxml.uuid.Generators;
import labs.lab6.exception.EntityNotFoundException;
import labs.lab6.model.Person;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PersonDao {
    private final Connection connection;
    static final String TABLE_NAME = "persons";
    public PersonDao() {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person add(Person person) throws SQLException {
        String insertPerson = String.format("insert into %s (first_name, last_name, pin, birth_date, notes, id) values (?,?,?,?,?,?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertPerson, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.getFirstName().stripIndent());
            ps.setString(2, person.getLastName().stripIndent());
            ps.setString(3, person.getPIN());
            ps.setDate(4, Date.valueOf(person.getBirthDate()));
            ps.setString(5, person.getNotes());
            ps.setObject(6, Generators.timeBasedEpochGenerator().generate());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet generatedKeys = ps.getGeneratedKeys();
                generatedKeys.next();
                person.setId(UUID.fromString(generatedKeys.getString("id")));
                return person;
            } else
                throw new IllegalArgumentException("Error while create person " + person);
        }
    }

    public List<Person> getAll() throws SQLException {
        String getAll = String.format("select * from %s;", TABLE_NAME);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        return getSortedByLastName(rs);
    }

    private List<Person> getSortedByLastName(ResultSet rs) throws SQLException {
        Set<Person> result = new TreeSet<>(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getPIN));
        while (rs.next()) {
            Person person = fromResultSet(rs);
            result.add(person);
        }
        return result.stream().toList();
    }


    public boolean update(Person person) throws SQLException {
        String updatePerson = String.format("update %s set first_name = ?, last_name = ?, birth_date = ?, notes = ? where id = uuid(?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(updatePerson)) {
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setDate(3, Date.valueOf(person.getBirthDate()));
            ps.setString(4, person.getNotes());
            ps.setString(5, person.getId().toString());

            return ps.executeUpdate() > 0;
        }
    }

    public Person deleteById(UUID id) throws SQLException {
        var result = getById(id);
        String deleteFlatType = String.format("delete from %s where id = uuid(?);", TABLE_NAME);
        PreparedStatement ps = connection.prepareStatement(deleteFlatType);
        ps.setString(1, id.toString());
        ps.execute();
        return result;
    }

    private Person getById(UUID id) throws SQLException {
        String get = String.format("select * from %s where id = uuid(?);", TABLE_NAME);
        PreparedStatement ps = connection.prepareStatement(get);
        ps.setString(1, id.toString());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return fromResultSet(rs);
        }
        throw new EntityNotFoundException("Flat type with id = " + id + " not found");

    }

    private Person fromResultSet(ResultSet rs) throws SQLException {
        Person person = Person.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .PIN(rs.getString("pin"))
                .birthDate(rs.getDate("birth_date").toLocalDate())
                .notes(rs.getString("notes"))
                .build();
        person.setId(UUID.fromString(rs.getString("id")));
        return person;
    }
}


