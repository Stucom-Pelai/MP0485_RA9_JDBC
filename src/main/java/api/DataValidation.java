package api;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataValidation {

    public static String wordNotEmpty(String message) {
        Scanner teclado = new Scanner(System.in);
        String wordNotEmpty;
        do {
            System.out.println(message);
            wordNotEmpty = teclado.nextLine();
        } while (wordNotEmpty.isEmpty());// pequeño control de errones 
        return wordNotEmpty;
    }

    public static int intFromTo(int min, int max, String message) {
        Scanner sc = new Scanner(System.in);
        int number = -1;
        do {
            try {
                System.out.println(message);
                number = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong option");
                sc.nextLine();
            }
        } while (number < min || number > max);
        return number;
    }

    public static char letterStringToChar(char a, char b, String message) {
        Scanner teclado = new Scanner(System.in);
        String letter;
        do {
            System.out.println(message);
            letter = teclado.nextLine();
        } while (!letter.equalsIgnoreCase(String.valueOf(a)) && !letter.equalsIgnoreCase(String.valueOf(b)));
        return letter.charAt(0);
    }
}
