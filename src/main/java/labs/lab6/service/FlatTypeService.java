package labs.lab6.service;

import labs.lab6.Main;
import labs.lab6.dao.FlatTypeDao;
import labs.lab6.exception.UniqnessException;
import labs.lab6.model.FlatType;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FlatTypeService {
    private static final Logger logger = LogManager.getLogger(FlatTypeService.class);
    private FlatTypeDao dao;

    public boolean add(FlatType type){
        logger.debug("start flat type adding");
        try {
            dao.add(type);
            logger.debug("flat type {} was added", type);
            logger.info("flat type {} was added", type);
        } catch (SQLException| UniqnessException e) {
            logger.error(e);
        }
        return false;
    }

    public List<FlatType> getAll(){
        logger.debug("start method for getting all flat types");
        try {
            var result = dao.getAll();
            logger.debug("finish method for getting all flat types");
            return result;
        } catch (SQLException e) {
            logger.error(e);
            return new ArrayList<>();
        }
    }
}
