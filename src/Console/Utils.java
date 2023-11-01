
package Console;

import java.util.ArrayList;
import java.util.Scanner;


public class Utils {
    public static String getString(String msg) {
        System.out.println(msg + ": ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static int getInt (String msg) {
        return Integer.parseInt(getString(msg));
    }
    public static void print(String title, ArrayList al) {
        System.out.println(title + "\n--------------------");
        for (Object object : al) {
            System.out.println(object);
        }
        System.out.println("--------------------");
        System.out.println("Total: " + al.size());
    }
}
