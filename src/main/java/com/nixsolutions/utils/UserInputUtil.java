package com.nixsolutions.utils;

import java.util.List;
import java.util.Scanner;

public class UserInputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private UserInputUtil() {
    }

    public static int getUserInput(String line, List<String> list) {
        int userInput;
        do {
            System.out.println(line);
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s%n", i, list.get(i));
            }
            userInput = SCANNER.nextInt();
        } while (userInput < 0 || userInput >= list.size());
        return userInput;
    }

    public static String getUserInput(String line) {
        String userInput;
        do {
            System.out.println(line);
            userInput = SCANNER.next();
        } while (userInput == null);
        return userInput;
    }
}
