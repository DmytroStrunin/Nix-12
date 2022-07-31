package com.nixsolutions;

import com.nixsolutions.command.Action;
import com.nixsolutions.command.Command;
import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.utils.BinaryTree;
import com.nixsolutions.utils.UserInputUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code Main} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Comparator<Vehicle> comparator = Comparator.comparing(Vehicle::getPrice)
                .reversed()
                .thenComparing(Vehicle::getManufacturer)
                .thenComparingInt(Vehicle::getCount);

        BinaryTree<Vehicle> binaryTree = new BinaryTree<>(comparator);
        binaryTree.add(new Auto("model1", Manufacturer.KIA, new BigDecimal(3), Body.COUPE));
        binaryTree.add(new Auto("model2", Manufacturer.BMW, new BigDecimal(2), Body.COUPE));
        binaryTree.add(new Auto("model3", Manufacturer.BMW, new BigDecimal(7), Body.COUPE));
        binaryTree.add(new Bus("model4", Manufacturer.ZAZ, new BigDecimal(5), 0));
        binaryTree.add(new Bus("model5", Manufacturer.KIA, new BigDecimal(8), 0));
        binaryTree.add(new Truck("model6",Manufacturer.ZAZ,new BigDecimal(5), 0));
        binaryTree.add(new Truck("model7",Manufacturer.BMW,new BigDecimal(8), 0));
        binaryTree.add(new Truck("model8",Manufacturer.BMW,new BigDecimal(3), 0));
        binaryTree.add(new Truck("model9",Manufacturer.ZAZ,new BigDecimal(2), 0));

        binaryTree.printTree();

        System.out.println("countLeftBranch = " + binaryTree.countLeftBranch());
        System.out.println("countRightBranch = " + binaryTree.countRightBranch());

        final Action[] actions = Action.values();
        final List<String> names = Arrays.stream(Action.values())
                .map(Enum::name)
                .toList();
        Command command;
        do {
            command = executeCommand(actions, names);
        } while (command != null);
    }

    private static Command executeCommand(Action[] actions, List<String> names) {
        int userInput = UserInputUtil.getUserInput("What you want:", names);
        final Action action = actions[userInput];
        return action.execute();
    }
}
