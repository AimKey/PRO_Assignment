package model;

import view.AppTools;

import java.util.ArrayList;

public class Lecturer {

    private String name, course, firstName;
    private ArrayList<String> lClasses = new ArrayList<>();
    private ArrayList<Integer> tLine = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    /**
     * Only use in load data
     */
    public Lecturer(String tName, String tCourse, ArrayList<Integer> tLine) {
        this.name = tName;
        this.course = tCourse;
        this.tLine = tLine;
    }

    //-----------------------------------------------------------------------
    public String toString() {
        return "Lecturer name: " + firstName + ", course: " + course + ", timeline: " + displayTLine();
    }
//    -------------------------------------------

    /**
     * @param s: A lecturer with String name, String course, ArrayList<String> lClasses
     * @return boolean
     */
    public static boolean checkValid(Lecturer s) {
        return AppTools.checkName(s.name);
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
                        this.tLine.add(Integer.parseInt(date[1]));
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
                tLine.sort((t1, t2) -> t1 - t2);
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
//            if (tLineNumber / 21 >= 1) {
//                result.add("SAT - Slot " + (tLineNumber - 20));
//            } else if (tLineNumber / 17 >= 1) {
//                result.add("FRI - Slot " + (tLineNumber - 16));
//            } else if (tLineNumber / 13 >= 1) {
//                result.add("THU - Slot " + (tLineNumber - 12));
//            } else if (tLineNumber / 9 >= 1) {
//                result.add("WED - Slot " + (tLineNumber - 8));
//            } else if (tLineNumber / 5 >= 1) {
//                result.add("TUE - Slot " + (tLineNumber - 4));
//            } else {
//                result.add("MON - Slot " + tLineNumber);
//            }
            int limit = School.numberOfSlots - 4;
            int length = School.weekdays.length - 1;
            for (int i = 0; i < 6; i++) {
                if (tLineNumber >= limit) {
                    String time = School.weekdays[length] + " - Slot " + (tLineNumber - limit);
                    result.add(time);
                    break;
                } else {
                    limit -= 4;
                    length -= 1;
                }
            }
        }
        return result.toString();
    }
//-------------------------------------------------------------------------------
//                A column is reserved for slots
//                A row is dedicated for weekdays
    public static int[] calTLine(int pos) {
        int[] result = new int[2];
        int limit = School.numberOfSlots - 4;
        int length = School.weekdays.length - 1;
        for (int i = 0; i < 6; i++) {
            if (pos >= limit) {
                result[0] = pos - limit;
                result[1] = length;
                break;
            } else {
                limit -= 4;
                length -= 1;
            }
        }
        return result;
    }
}