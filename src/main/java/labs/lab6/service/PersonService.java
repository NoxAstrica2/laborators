package labs.lab6.service;

import labs.lab6.dao.FlatDao;
import labs.lab6.dao.FlatTypeDao;
import labs.lab6.exception.UniqnessException;
import labs.lab6.model.Flat;
import labs.lab6.model.FlatType;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PersonService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    private FlatDao flatDao;

    public List<Flat> allFlatsByPersonPIN(String pin){
        logger.debug("start method for getting flats for peron with pin = {}", pin);
        List<Flat> result = new ArrayList<>();
        try {
            result = flatDao.getByOwnerPIN(pin);
            logger.debug("got all flats for person with pin = {}", pin);
        } catch (SQLException| UniqnessException e) {
            logger.error(e);
        }
        logger.info("got all flats for person with pin = {}", pin);
        return result;
    }



}

