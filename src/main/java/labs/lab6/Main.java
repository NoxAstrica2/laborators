package labs.lab6;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import labs.lab6.dao.DataSource;
import labs.lab6.dao.FlatDao;
import labs.lab6.dao.FlatTypeDao;
import labs.lab6.dao.PersonDao;
import labs.lab6.model.Flat;
import labs.lab6.model.FlatType;
import labs.lab6.model.Person;
import labs.lab6.service.FlatTypeService;
import labs.lab6.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class Main {

    public static void main(String[] args) {


//
//        String uri = "mongodb://mongoadmin:mongopasswd@localhost:27017";
//        MongoClient mongoClient = MongoClients.create(uri);
//        MongoDatabase database = mongoClient.getDatabase("sample_mflix");
//        MongoCollection<Document> collection = database.getCollection("movies");
//
//        collection.insertOne(new Document()
////                .append("_id", new ObjectId())
//                .append("title", "Silly Video")
//                .append("genres", Arrays.asList("Action", "Adventure")));
//
//        Bson projectionFields = Projections.fields(
//                Projections.include("title", "year"),
//                Projections.excludeId());
//
//        MongoCursor<Document> cursor = collection.find(gt("year", 1900))
//                .projection(projectionFields)
//                .iterator();
//
//        cursor = collection.find(eq("title", "Silly Video"))
//                .iterator();
//
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }
//        database.getCollection("movies").drop();
//
//
//        System.exit(0);

        try {
            DataSource.createTablesStructure();


        FlatType type = new FlatType("First three-rooms", 3, 45.3, null);
        FlatTypeDao fd = new FlatTypeDao();
        FlatTypeService flatTypeService = new FlatTypeService(fd);
        PersonService pService = new PersonService(new FlatDao());
        System.out.println(pService.allFlatsByPersonPIN("01928b3c-7a1a-79e2-8a65-172a7613aaad"));
        fd.add(type);

        type = new FlatType("Three", 3, 45.3, null);
//        fd.add(type);

//        System.out.println(flatTypeService.getAll());
//        System.out.println(fd.getByTitle("First"));
//        fd.update(new FlatType(UUID.fromString("01928a6b-45e5-7a2f-8ecf-a713a5fc0a04"), "Other wo", 20, 0.0, null));
        System.out.println(fd.getByTitlePart("ee"));
//        System.out.println(new FlatTypeDao().deleteById(UUID.fromString("01928a6b-45e5-7a2f-8ecf-a713a5fc0a04")));
//        System.out.println(new FlatTypeDao().getAll());

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
