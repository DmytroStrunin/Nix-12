package com.nixsolutions.utils;

import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@code VehicleUtil} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public final class VehicleUtil {
    private VehicleUtil(){}

    public static List<Vehicle> vehiclesCreate(List<Map<String, Object>> list) {
        Function<Map<String, Object>, Vehicle> function = map -> new Auto(
                (String) map.get("model")
                , (Manufacturer) map.get("manufacturer")
                , (BigDecimal) map.get("price")
                , (Body) map.get("body")
        );
        return list.stream().map(function).toList();
    }
}
