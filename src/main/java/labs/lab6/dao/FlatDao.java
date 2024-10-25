package labs.lab6.dao;

import com.fasterxml.uuid.Generators;
import labs.lab6.model.Flat;
import labs.lab6.model.FlatType;
import labs.lab6.model.Person;

import java.sql.*;
import java.util.*;

public class FlatDao {
    private final Connection connection;
    static final String TABLE_NAME = "flats";

    public FlatDao() {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Flat add(Flat flat) throws SQLException {
        String insertPerson = String.format("insert into %s (flat_type_id, number, person_id, id) values (?,?,?,?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertPerson, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, flat.getType().id());
            ps.setString(2, flat.getNumber());
            ps.setObject(3, flat.getOwner().getId());
            ps.setObject(4, Generators.timeBasedEpochGenerator().generate());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet generatedKeys = ps.getGeneratedKeys();
                generatedKeys.next();
                flat.setId(UUID.fromString(generatedKeys.getString("id")));
                return flat;
            } else
                throw new IllegalArgumentException("Error while create flat " + flat);
        }
    }

    public List<Flat> getAll() throws SQLException {
        String getAll = String.format("select %1$s.id as flat_id,  %2$s.id as type_id, %3$s.id as owner_id, * from %1$s join %2$s on %1$s.flat_type_id=%2$s.id join %3$s on %1$s.person_id=%3$s.id;", TABLE_NAME, FlatTypeDao.TABLE_NAME, PersonDao.TABLE_NAME);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        return getSortedByNumber(rs);
    }

    public List<Flat> getByOwnerPIN(String pin) throws SQLException {
        String get = String.format("select %1$s.id as flat_id,  %2$s.id as type_id, %3$s.id as owner_id, * from %1$s join %2$s on %1$s.flat_type_id=%2$s.id join %3$s on %1$s.person_id=%3$s.id where pin = ?;", TABLE_NAME, FlatTypeDao.TABLE_NAME, PersonDao.TABLE_NAME);

        PreparedStatement ps = connection.prepareStatement(get);
        ps.setString(1, pin);
        ResultSet rs = ps.executeQuery();
        return getSortedByNumber(rs);
    }

    private List<Flat> getSortedByNumber(ResultSet rs) throws SQLException {
        Set<Flat> result = new TreeSet<>();
        while (rs.next()) {
            Flat flat = fromResultSet(rs);
            result.add(flat);
        }
        return result.stream().toList();
    }


    private Flat fromResultSet(ResultSet rs) throws SQLException {
        return new Flat.FlatBuilder(rs.getString("number"))
                .type(new FlatType(UUID.fromString(rs.getString("type_id")), rs.getString("title"), rs.getInt("room_amount"), rs.getDouble("area"), rs.getString("plan_url")))
                .owner(Person.builder()
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .PIN(rs.getString("pin"))
                        .birthDate(rs.getDate("birth_date").toLocalDate())
                        .notes(rs.getString("notes"))
                        .build())
                .build();
    }

}


