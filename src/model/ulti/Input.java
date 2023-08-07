package model.ulti;

import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);
    public static int choiceIntegerInput(String request) {
        int choice;
        do {
            try {
                System.out.print(request);
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! " + e.getMessage() + " .Please try again!");
            }
        } while (true);
        return choice;
    }
    public static String promt(String request) {
        System.out.println(request);
        return sc.nextLine();
    }
    public static String prompt(String request, String regexPattern) {
        String text;
        do {
            text = promt(request);
            if(Validation.validate(text, regexPattern)) {
                System.out.println("Invalid Input! Wrong format! ");
            }
        } while (Validation.validate(text, regexPattern));
        return text;
    }
}
