/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author phamm
 */
public class Classroom {

    private ArrayList<Student> sList = new ArrayList();
    private String classID;

    public ArrayList<Student> getsList() {
        return sList;
    }

    public void setsList(ArrayList<Student> sList) {
        this.sList = sList;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public Classroom() {
    }

    public Classroom(String classID) {
        this.classID = classID;
    }
    
    /**
     * Dung trong TH lop chua ton tai (tao moi)
     * @param s 
     */
    public void addStd(Student s) {
        this.sList.add(s);
    }
/**
 * Ham nay dung trong truong hop lop da ton tai
 * @param s
 * @param school 
 */
    public void addStd(Student s, School school) {
        Classroom cr = school.findClass(s.getClassID());
        cr.sList.add(s);
    }

    public void display() {
        System.out.println("Student of class " + classID);
        for (Student student : sList) {
            System.out.println(student);
        }
    }

}
