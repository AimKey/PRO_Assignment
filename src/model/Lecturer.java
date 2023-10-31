package model;

import java.util.ArrayList;
import java.util.Collections;
import view.AppTools;
import view.ManageView;

public class Lecturer {

    private String lName, lCourse;
    private ArrayList<String> lClasses = new ArrayList<>();
    private ArrayList<Integer> tLine = new ArrayList<>();

    public String gettName() {
        return lName;
    }

    public void settName(String tName) {
        this.lName = tName;
    }

    public String gettCourse() {
        return lCourse;
    }

    public void settCourse(String tCourse) {
        this.lCourse = tCourse;
    }

    public ArrayList gettClasses() {
        return lClasses;
    }

    public void settClasses(ArrayList tClasses) {
        this.lClasses = tClasses;
    }

    public Lecturer(String tName, String tCourse) {
        this.lName = tName;
        this.lCourse = tCourse;
    }

    public ArrayList<Integer> gettLine() {
        return tLine;
    }

    public void settLine(ArrayList<Integer> tLine) {
        this.tLine = tLine;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlCourse() {
        return lCourse;
    }

    public void setlCourse(String lCourse) {
        this.lCourse = lCourse;
    }

    public ArrayList<String> getlClasses() {
        return lClasses;
    }

    public void setlClasses(ArrayList<String> lClasses) {
        this.lClasses = lClasses;
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
        return "Lecturer name: " + lName + ", course: " + lCourse + ", timeline: " + tLine.toString();
    }
//    -------------------------------------------

    /**
     *
     * @param s: A lecturer with String name, String course, ArrayList<String> lClasses
     * @return
     */
    public static boolean checkValid(Lecturer s) {
        boolean check = AppTools.checkName(s.lName);
        return check;
    }

    public void setupTimeline() {
        try {
            do {
//            mon-4
                String[] date = AppTools.getString("Enter date (Ex: MON-4)").split("-");
//            [mon],[4]
                if (Integer.parseInt(date[1]) < 1 || Integer.parseInt(date[1]) > 4) {
                    throw new Exception();
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
                System.out.println(tLine.toString());
            } while (AppTools.getString("Continue ? (y/n)").equals("y"));
            
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }
//-------------------------------------------------------------------------------

}
