package com.nixsolutions.command;

import com.nixsolutions.model.Vehicle;
import com.nixsolutions.model.VehicleType;
import com.nixsolutions.service.AutoService;
import com.nixsolutions.service.BusService;
import com.nixsolutions.service.TruckService;
import com.nixsolutions.utils.UserInputUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Update implements Command {
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final TruckService TRUCK_SERVICE = TruckService.getInstance();

    @Override
    public void execute() {
        final VehicleType[] values = VehicleType.values();
        final List<String> names =
                Arrays.stream(values)
                        .map(Enum::name)
                        .toList();
        final int userInput = UserInputUtil.getUserInput("What you want to update?", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case AUTO -> execute(AUTO_SERVICE.update(UserInputUtil.getUserInput("What Auto you want to update?", AUTO_SERVICE.findAll().stream().map(String::valueOf).toList())));
            case BUS -> execute(BUS_SERVICE.update(UserInputUtil.getUserInput("What Bus you want to update?", BUS_SERVICE.findAll().stream().map(String::valueOf).toList())));
            case TRUCK -> execute(TRUCK_SERVICE.update(UserInputUtil.getUserInput("What Truck you want to update?", TRUCK_SERVICE.findAll().stream().map(String::valueOf).toList())));
            default -> throw new IllegalArgumentException("Cannot build " + value);
        }
    }

    private void execute(Vehicle vehicle) {
        final int value = Integer.parseInt(UserInputUtil.getUserInput("0. Set price" + "\n" + "1. Set model"));
        switch (value) {
            case 0 -> vehicle.setPrice(new BigDecimal(UserInputUtil.getUserInput("Set price")));
            case 1 -> vehicle.setModel(UserInputUtil.getUserInput("Set model"));
            default -> execute(vehicle);
        }
    }
}
