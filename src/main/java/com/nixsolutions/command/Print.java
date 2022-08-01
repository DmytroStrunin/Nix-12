package com.nixsolutions.command;

import com.nixsolutions.model.VehicleType;
import com.nixsolutions.service.AutoService;
import com.nixsolutions.service.BusService;
import com.nixsolutions.service.TruckService;
import com.nixsolutions.utils.UserInputUtil;

import java.util.Arrays;
import java.util.List;

public class Print implements Command {
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
        final int userInput = UserInputUtil.getUserInput("What you want to print:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case AUTO -> AUTO_SERVICE.printAll();
            case BUS -> BUS_SERVICE.printAll();
            case TRUCK -> TRUCK_SERVICE.printAll();
            default -> throw new IllegalArgumentException("Cannot build " + value);
        }
    }
}
