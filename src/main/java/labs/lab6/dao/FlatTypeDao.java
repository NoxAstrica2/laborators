package labs.lab6.dao;

import com.fasterxml.uuid.Generators;
import labs.lab6.exception.EntityNotFoundException;
import labs.lab6.model.FlatType;
import labs.lab6.exception.UniqnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class FlatTypeDao {
    private static final Logger logger = LogManager.getLogger(FlatTypeDao.class);
    private final Connection connection;
    static final String TABLE_NAME = "flat_types";

    public FlatTypeDao() {
        try {
            connection = DataSource.getConnection();
            logger.debug("Connection to database was estgablished");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean isExistsByTitle(String title) throws SQLException {
        logger.debug("start method for checkin if flat type with title {} exists", title);
        try {
            getByTitle(title);
            logger.debug("flat type with title {} exists", title);
            return true;
        } catch (EntityNotFoundException e) {
            logger.warn("flat type with title {} doesn't exist", title);
            return false;
        }
    }

    public FlatType add(FlatType type) throws SQLException {
        logger.debug("start method for add flat type {}", type);

        if (isExistsByTitle(type.title())) {
            logger.error("Flat type with title = {} already exists", type.title());
            throw new UniqnessException("Flat type with title = " + type.title() + " already exists");
        }
        String insertFlatType = String.format("insert into %s (title, room_amount, area, plan_url, id) values (?,?,?,?,?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertFlatType, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, type.title().stripIndent());
            ps.setInt(2, type.roomAmount());
            ps.setDouble(3, type.area());
            ps.setString(4, type.planUrl());
            ps.setObject(5, Generators.timeBasedEpochGenerator().generate());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                generatedKeys.next();
                UUID id = UUID.fromString(generatedKeys.getString("id"));
                logger.debug("Flat type {} was added", type);
                return new FlatType(id, type.title(), type.roomAmount(), type.area(), type.planUrl());
            } else {
                logger.error("Error while create flat type {}", type);
            }
            throw new IllegalArgumentException("Error while create flat type " + type);
        }
    }

    public List<FlatType> getAll() throws SQLException {
        String getAll = String.format("select * from %s;", TABLE_NAME);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        return getSortedByTitleFlatTypes(rs);
    }

    private List<FlatType> getSortedByTitleFlatTypes(ResultSet rs) throws SQLException {
        Set<FlatType> result = new TreeSet<>(Comparator.comparing(o -> o.title().toLowerCase()));
        while (rs.next()) {
            FlatType ft = fromResultSet(rs);
            result.add(ft);
        }
        return result.stream().toList();
    }

    public List<FlatType> getByTitlePart(String text) throws SQLException {
        String getAll = String.format("select * from %s where lower(title) like ?;", TABLE_NAME);
        PreparedStatement ps = connection.prepareStatement(getAll);
        ps.setString(1, "%" + text.stripIndent().toLowerCase() + "%");
        ResultSet rs = ps.executeQuery();

        return getSortedByTitleFlatTypes(rs);
    }

    public FlatType getByTitle(String text) throws SQLException {
        String getAll = String.format("select * from %s where lower(title) = ?;", TABLE_NAME);
        PreparedStatement ps = connection.prepareStatement(getAll);
        ps.setString(1, text.stripIndent().toLowerCase());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return fromResultSet(rs);
        }
        throw new EntityNotFoundException("Flat type with title " + text + " doesn't exist");
    }


    public boolean update(FlatType type) throws SQLException {
        FlatType oldFlatType = getById(type.id());
        if (!oldFlatType.title().equals(type.title()) && isExistsByTitle(type.title())) {
            throw new UniqnessException("Flat type with title = " + type.title() + " already exists");
        }
        String updateFlatType = String.format("update %s set title = ?, room_amount = ?, area = ?, plan_url = ? where id = uuid(?);", TABLE_NAME);
        PreparedStatement ps = connection.prepareStatement(updateFlatType);
        ps.setString(1, type.title());
        ps.setInt(2, type.roomAmount());
        ps.setDouble(3, type.area());
        ps.setString(4, type.planUrl());
        ps.setString(5, type.id().toString());

        return ps.executeUpdate() > 0;

    }

    public FlatType deleteById(UUID id) throws SQLException {
        var result = getById(id);
        String deleteFlatType = String.format("delete from %s where id = uuid(?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(deleteFlatType)) {
            ps.setString(1, id.toString());
            ps.execute();
        }
        return result;
    }

    private FlatType getById(UUID id) throws SQLException {
        String get = String.format("select * from %s where id = uuid(?);", TABLE_NAME);

        PreparedStatement ps = connection.prepareStatement(get);
        ps.setString(1, id.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }
        throw new EntityNotFoundException("Flat type with id = " + id + " not found");
    }

    private FlatType fromResultSet(ResultSet rs) throws SQLException {
        return new FlatType(UUID.fromString(rs.getString("id")),
                rs.getString("title"),
                rs.getInt("room_amount"),
                rs.getDouble("area"),
                rs.getString("plan_url"));
    }
}


