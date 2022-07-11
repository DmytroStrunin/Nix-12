package com.nixsolutions.service;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.AutoRepository;
import com.nixsolutions.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final CrudRepository<Auto> REPOSITORY = new AutoRepository();
    private static final Random RANDOM = new Random();


    public List<Auto> create(int count) {
        List<Auto> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Auto auto = new Auto(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomBody()
            );
            result.add(auto);
            LOGGER.debug("Created auto {}", auto.getId());
        }
        return result;
    }

    private Body getRandomBody() {
        final Body[] values = Body.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void save(List<Auto> autos) {
        REPOSITORY.create(autos);
    }

    public void printAll() {
        for (Object object : REPOSITORY.getAll()) {
            System.out.println(object);
        }
    }

    public boolean update(Auto auto) {
        if (REPOSITORY.getById(auto.getId()) != null) {
            LOGGER.debug("Update auto {}", auto.getId());
        }
        return REPOSITORY.update(auto);
    }

    public boolean delete(String id) {
        if (REPOSITORY.delete(id)) {
            LOGGER.debug("Remove auto {}", id);
            return true;
        }
        return false;
    }

    Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
