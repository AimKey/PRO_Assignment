package view;

import model.School;

import java.util.ArrayList;
import java.util.Scanner;

public class AppTools {

    public static String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg + ": ");
        return sc.nextLine().trim();
    }

    public static int getInt(String msg) {
        return Integer.parseInt(getString(msg));
    }

    public static boolean checkName(String name) {
        if (!name.matches("^[A-Za-z ]+$")) {
            System.out.println("Name is not allowed to have numbers or special characters !");
            return false;
        } else return true;
    }

    public static <T> boolean show(ArrayList<T> target, String title) {
        if (!target.isEmpty()) {
            System.out.println("Available " + title + ":");
            for (int i = 0; i < target.size(); i++) {
                System.out.println((i + 1) + ". " + target.get(i));
            }
        } else {
            System.out.println("No target is found, please create " + title + " first!");
            return false;
        }
        System.out.println("-----------------------------");
        return true;
    }

    public static <T> void printArr(T[][] array) {
        String[] weekdays = School.weekdays;
        for (int i = 0; i < array.length; i++) {
//            Columns
            for (int j = 0; j < array[0].length; j++) {
                if (i == 0) {
                    System.out.printf("%-14s", weekdays[j]);
                    continue;
                }
                if (j == 0) {
                    String sl = "Slot " + i + ":";
                    System.out.printf("%-14s", sl);
                } else {
                    String s = (array[i][j] == null ? " - " : (String) array[i][j]);
                    System.out.printf("%-14s", s);
                }
            }
            System.out.println();
        }
    }
}
