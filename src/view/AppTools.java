package view;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class AppTools {
    public static String getString (String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg + ": ");
        return sc.nextLine().trim();
    }

    public static int getInt (String msg) {
        return Integer.parseInt(getString(msg));
    }

    

}
