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
public class School {

    private ArrayList<Classroom> classrooms = new ArrayList();
    private ArrayList<String> courses = new ArrayList();

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public School() {
    }

    /**
     * Loop through the classrooms ArrayList and return the Classroom
     *
     * @param findID
     * @return Classroom
     */
    public Classroom findClass(String findID) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassID().equals(findID)) {
                return classroom;
            }
        }
        return null;
    }

    /**
     * Display the entered classroom. This function stays in the school object
     * because it need to find the data in the school
     *
     * @param cr
     */
    public void displayClass(Classroom cr) {
        cr = findClass(cr.getClassID());
        cr.display();
    }

    public void addClasses(Classroom cls) {
        classrooms.add(cls);
    }

    /**
     * Use to show all classrooms in a school. Will display Students, Lecturers,
     * and course of each class room
     */
    public void showClasses() {
        System.out.println("List of available classes:");
        for (Classroom classroom : classrooms) {
            classroom.display();
        }
    }
}
