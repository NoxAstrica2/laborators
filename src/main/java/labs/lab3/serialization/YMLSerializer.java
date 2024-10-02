package labs.lab3.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YMLSerializer<T> implements Serialization<T> {
    private ObjectMapper mapper;

    public YMLSerializer(){
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public T toEntity(String object) {
        return null;
    }

    @Override
    public List<T> toEntityList(String objectCollection) {
        return null;
    }

    @Override
    public String fromEntity(T object) {
        return null;
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
