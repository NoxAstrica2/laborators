package labs.lab3.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONSerializer<T> implements Serialization<T> {
    private final ObjectMapper mapper;
    private final Class<T> clazz;

    public JSONSerializer(Class<T> clazz){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.clazz = clazz;
    }

    @Override
    public T toEntity(String object) throws JsonProcessingException {
        return mapper.readValue(object, clazz);
    }

    @Override
    public List<T> toEntityList(String objectCollection) throws JsonProcessingException {
        return mapper.readValue(objectCollection, new TypeReference<List<T>>() {});
    }

    @Override
    public String fromEntity(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    @Override
    public String fromEntityList(List<T> objectCollection) {
        return null;
    }

    @Override
    public List<T> toEntityListFromFile(String fileName) {
        return null;
    }

    @Override
    public void fromEntityToFile(T object, String fileName) throws IOException {
        mapper.writeValue(new File(fileName), object);
    }

    @Override
    public void fromEntityListToFile(List<T> objectCollection, String fileName) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objectCollection);
    }

}
