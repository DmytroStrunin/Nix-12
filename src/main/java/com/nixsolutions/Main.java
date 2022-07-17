package com.nixsolutions;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.repository.AutoRepository;
import com.nixsolutions.repository.BusRepository;
import com.nixsolutions.repository.TruckRepository;
import com.nixsolutions.service.AutoService;
import com.nixsolutions.service.BusService;
import com.nixsolutions.service.TruckService;


import java.math.BigDecimal;
import java.util.List;

/**
 * The {@code Main} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class Main {
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(new BusRepository());
    private static final TruckService TRUCK_SERVICE = new TruckService(new TruckRepository());

    public static void main(String[] args) {
        final List<Auto> autos = AUTO_SERVICE.createAndSaveAutos(2);
        final List<Bus> buses = BUS_SERVICE.create(2);
        final List<Truck> trucks = TRUCK_SERVICE.create(2);
        AUTO_SERVICE.saveAutos(autos);
        BUS_SERVICE.save(buses);
        TRUCK_SERVICE.save(trucks);
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        TRUCK_SERVICE.printAll();

        Bus bus = buses.get(0);
        bus.setModel("Update model");
        bus.setManufacturer(Manufacturer.KIA);
        bus.setPrice(BigDecimal.ZERO);
        bus.setNumberOfPassengers(9999);
        /*
        Я понимаю что фактически уже изменил
         */
        System.out.println("****Update****");
        BUS_SERVICE.update(bus);
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        TRUCK_SERVICE.printAll();

        System.out.println("****Delete****");
        String truckId = trucks.get(0).getId();
        TRUCK_SERVICE.delete(truckId);
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        TRUCK_SERVICE.printAll();
    }
}
