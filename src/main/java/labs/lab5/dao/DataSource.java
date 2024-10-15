package labs.lab5.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import labs.lab5.model.Flat;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        try (InputStream input = MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            config = new HikariConfig(prop);
            ds = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


//    static {
//        config.setJdbcUrl( "jdbc_url" );
//        config.setUsername( "database_username" );
//        config.setPassword( "database_password" );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        ds = new HikariDataSource( config );
//    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    //    create table flat_types
//            (
//                    id          uuid primary key,
//                    title       varchar,
//                    room_amount int,
//                    area        numeric(5, 2),
//    plan_url    varchar
//);
//
//    create table persons
//            (
//                    id         uuid primary key,
//                    first_name varchar,
//                    last_name  varchar,
//                    pin        varchar unique,
//                    birth_date date,
//                    notes      text
//            );
//
//    create table flats
//            (
//                    id           uuid primary key,
//                    flat_type_id uuid,
//                    number       varchar unique,
//                    person_id    uuid,
//                    foreign key (flat_type_id) references flat_types (id),
//    foreign key (person_id) references persons (id)
//            );
    public static void createTablesStructure() throws SQLException {
        String createFlatType = """
                create table if not exists flat_types (id uuid primary key, title varchar, room_amount int, 
                area numeric(5, 2), plan_url varchar);
                """;
        String createPerson = """
                create table if not exists persons
                (
                        id         uuid primary key,
                        first_name varchar,
                        last_name  varchar,
                        pin        varchar unique,
                        birth_date date,
                        notes      text
                )
                """;

        String createFlat = """
                create table if not exists flats
                (
                        id           uuid primary key,
                        flat_type_id uuid,
                        number       varchar unique,
                        person_id    uuid,
                        foreign key (flat_type_id) references flat_types (id),
                        foreign key (person_id) references persons (id)
                );
                """;

        String createAccounting = """
                create table if not exists accountings
                  (
                      id           uuid primary key,
                      flat_id uuid not null,
                      month int not null,
                      year int not null,
                      pay_time timestamp with time zone default now(),
                      req_amount numeric(5, 2) not null,
                      actual_amount numeric(5, 2) not null,
                      foreign key (flat_id) references flats (id),
                      unique (flat_id, month, year)
                  );""";

        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.execute(createFlatType);
            st.execute(createPerson);
            st.execute(createFlat);
            st.execute(createAccounting);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
