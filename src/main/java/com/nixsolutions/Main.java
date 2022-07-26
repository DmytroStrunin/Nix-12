package com.nixsolutions;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.utils.Garage;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * The {@code Main} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

//        Comparator<Vehicle> comparator = new Comparator<Vehicle>() {
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return o2.getPrice().compareTo(o1.getPrice());
//            }
//        }.thenComparing(new Comparator<Vehicle>(){
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return o1.getManufacturer().compareTo(o2.getManufacturer());
//            }
//        }).thenComparing(new Comparator<Vehicle>() {
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return Integer.compare(o1.getCount(), o2.getCount());
//            }
//        });

        Comparator<Vehicle> comparator = Comparator.comparing(Vehicle::getPrice)
                .reversed()
                .thenComparing(Vehicle::getManufacturer)
                .thenComparingInt(Vehicle::getCount);

        Garage<Vehicle> garage = new Garage<>();
        garage.add(new Auto("model",Manufacturer.KIA,new BigDecimal(3), Body.COUPE), 1);
        garage.add(new Auto("model",Manufacturer.BMW,new BigDecimal(2), Body.COUPE), 2);
        garage.add(new Auto("model",Manufacturer.BMW,new BigDecimal(7), Body.COUPE), 3);
        garage.add(new Bus("model",Manufacturer.ZAZ,new BigDecimal(5), 0), 4);
        garage.add(new Bus("model",Manufacturer.KIA,new BigDecimal(8), 0), 5);
        garage.add(new Truck("model",Manufacturer.ZAZ,new BigDecimal(5), 0), 6);
        garage.add(new Truck("model",Manufacturer.BMW,new BigDecimal(8), 0), 7);
        garage.add(new Truck("model",Manufacturer.BMW,new BigDecimal(3), 0), 8);
        garage.add(new Truck("model",Manufacturer.ZAZ,new BigDecimal(2), 0),9);

        System.out.println("***before sort***");
        garage.forEach(System.out::println);

        garage.sort(comparator);
        System.out.println("***after sort***");

        for (Vehicle vehicle : garage) {
            System.out.println(vehicle);
        }
    }
}
