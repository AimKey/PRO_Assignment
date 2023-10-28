
package model;

import java.util.ArrayList;

public class Lecturer {
    private String tName, tCourse;
    private ArrayList<String> tClasses = new ArrayList<>();

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettCourse() {
        return tCourse;
    }

    public void settCourse(String tCourse) {
        this.tCourse = tCourse;
    }

    public ArrayList gettClasses() {
        return tClasses;
    }

    public void settClasses(ArrayList tClasses) {
        this.tClasses = tClasses;
    }

    public Lecturer(String tName, String tCourse, String classID) {
        this.tName = tName;
        this.tCourse = tCourse;
        this.tClasses.add(classID);
    }
    public void assignClass(String c) {
        this.tClasses.add(c);
    }
    
    public String toString() {
        return "Lecturer name: " + tName + ", course: " + tCourse + ", classes: " + tClasses;
    }
}
