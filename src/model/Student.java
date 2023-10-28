
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {

    private String name, classID;
    private LocalDate dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getDob() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dob);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Student() {
    }

    public Student(String name, String classID, String dob) {
        this.name = name;
        this.classID = classID;
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }

    @Override
    public String toString() {
        return "Student name: " + name + ", classID: " + classID + ", Dob: " + getDob();
    }
//    ---------------------------------

    public static String formatDate(String userDate) {
        String[] dateTime = userDate.split("/");
        if (dateTime[0].length() == 1) {
            dateTime[0] = "0" + dateTime[0];
        }
        if (dateTime[1].length() == 1) {
            dateTime[1] = "0" + dateTime[1];
            String newDateTime = String.join("/", dateTime);
            return newDateTime;
        }
        return userDate;
    }
}
