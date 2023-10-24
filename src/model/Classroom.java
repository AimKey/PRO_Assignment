/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import view.ManageView;
import static view.ManageView.school;

/**
 *
 * @author phamm
 */
public class Classroom {

    private ArrayList<Student> sList = new ArrayList();
    private ArrayList<Lecturer> lList = new ArrayList();
    private String classID;

    public ArrayList<Lecturer> getlList() {
        return lList;
    }

    public void setlList(ArrayList<Lecturer> lList) {
        this.lList = lList;
    }
    
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

    public static void addStd(String sName, String sClassID, String sDob) {

        try {
            Student s = new Student(sName, sClassID, sDob);
            Classroom cr = ManageView.school.searchClass(p -> p.classID.equals(sClassID));
            if (cr == null) {
                Classroom n = new Classroom(s.getClassID());
                n.sList.add(s);
                ManageView.school.addClasses(n);
            } else {
                cr.sList.add(s);
            }
        } catch (Exception e) {
            System.out.println("Tao that bai!\nWrong birthday!");
        }
    }
    
    
    public void addLec(Lecturer l) {
        lList.add(l);
    }
    
    public void display() {
        System.out.println("Teach by Lecturers: ");
        for (Lecturer l : lList) {
            System.out.println(l);
        }
        System.out.println("Student of class " + this.classID);
        for (Student student : sList) {
            System.out.println(student);
        }
        System.out.println("-----------------------------");
    }

}
