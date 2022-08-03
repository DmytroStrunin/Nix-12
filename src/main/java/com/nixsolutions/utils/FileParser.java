package com.nixsolutions.utils;

import com.nixsolutions.model.vehicle.Vehicle;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.nixsolutions.utils.VehicleUtil.vehiclesCreate;

/**
 * The {@code FileParser} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public final class FileParser {

    private FileParser(){
    }

    public static List<Vehicle> parseJSON(Files files){
        List<Map<String, Object>> list = null;

        return vehiclesCreate(list);
    }
}
