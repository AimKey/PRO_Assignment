package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import view.AppTools;

public class Lecturer {

    private String name, course;
    private ArrayList<String> lClasses = new ArrayList<>();
    private ArrayList<Integer> tLine = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<String> getlClasses() {
        return lClasses;
    }

    public void setlClasses(ArrayList<String> lClasses) {
        this.lClasses = lClasses;
    }

    public ArrayList<Integer> gettLine() {
        return tLine;
    }

    public void settLine(ArrayList<Integer> tLine) {
        this.tLine = tLine;
    }
   
//-----------------------------------------------------------------------
    public Lecturer(String tName, String tCourse) {
        this.name = tName;
        this.course = tCourse;
    }
    public Lecturer(String tName, String tCourse, ArrayList<Integer> tLine) {
        this.name = tName;
        this.course = tCourse;
        this.tLine = tLine;
    }
//-----------------------------------------------------------------------
    /**
     * Sau nay bo
     *
     * @param classID
     */
    public void assignClass(String classID) {
        this.lClasses.add(classID);
    }
//-----------------------------------------------------------------------

    public String toString() {
        return "Lecturer name: " + name + ", course: " + course + ", timeline: " + displayTLine();
    }
//    -------------------------------------------

    /**
     *
     * @param s: A lecturer with String name, String course, ArrayList<String> lClasses
     * @return
     */
    public static boolean checkValid(Lecturer s) {
        boolean check = AppTools.checkName(s.name);
        return check;
    }

    public void setupTimeline() {
        System.out.println("Enter to cancel");
        try {
            tLine = new ArrayList<>();
            do {
//            mon-4
                String[] date = AppTools.getString("Enter schedule (Ex: mon-4)").split("-");
                if (date.length == 1) {
                    System.out.println("Canceled!");
                    break;
                }
//            [mon],[4]
                if (Integer.parseInt(date[1]) < 1 || Integer.parseInt(date[1]) > 4) {
                    System.out.println("Wrong input!");
                    continue;
                }
                switch (date[0].toLowerCase()) {
                    case "mon": {
                        this.tLine.add(0 + Integer.parseInt(date[1]));
                        break;
                    }
                    case "tue": {
                        this.tLine.add(4 + Integer.parseInt(date[1]));
                        break;
                    }
                    case "wed": {
                        this.tLine.add(8 + Integer.parseInt(date[1]));
                        break;
                    }
                    case "thu": {
                        this.tLine.add(12 + Integer.parseInt(date[1]));
                        break;
                    }
                    case "fri": {
                        this.tLine.add(16 + Integer.parseInt(date[1]));
                        break;
                    }
                    case "sat": {
                        this.tLine.add(20 + Integer.parseInt(date[1]));
                        break;
                    }
                    default: {
                        System.out.println("Wrong format!");
                    }
                }
                Collections.sort(tLine, (t1, t2) -> t1 - t2);
                System.out.println(displayTLine());
            } while (true);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
    }
//-------------------------------------------------------------------------------

    public String displayTLine() {
        ArrayList<String> result = new ArrayList<>();
        for (Integer tLineNumber : this.tLine) {
            if (tLineNumber / 21 >= 1) {
                result.add("SAT - Slot " + (tLineNumber - 20));
            } else if (tLineNumber / 17 >= 1) {
                result.add("FRI - Slot " + (tLineNumber - 16));
            } else if (tLineNumber / 13 >= 1) {
                result.add("THU - Slot " + (tLineNumber - 12));
            } else if (tLineNumber / 9 >= 1) {
                result.add("WED - Slot " + (tLineNumber - 8));
            } else if (tLineNumber / 5 >= 1) {
                result.add("TUE - Slot " + (tLineNumber - 4));
            } else {
                result.add("MON - Slot " + tLineNumber);
            }
        }
        return result.toString();
    }
}