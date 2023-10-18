/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author phamm
 */
public class Student extends Person {
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
    
    public void add(Student s) {
        System.out.println("Adding");
    }
    
    @Override
    public String toString() {
        return "Student name: " + name + ", classID: " + classID + ", Dob: " + getDob();
    }
}
