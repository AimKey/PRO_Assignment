/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamm
 */
public class Lecturer extends Person {
    private String name, classID;
    
    @Override
    public String toString() {
        return "Student name: " + name + ", classID: " + classID;
    }
}
