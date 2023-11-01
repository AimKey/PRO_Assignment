package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ManageView;

public class School {

    private ArrayList<Classroom> classrooms = new ArrayList();
    private ArrayList<String> courses = new ArrayList();

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public School() {
    }
//    Copy Classroom arraylist

    /**
     * Find class using predicate
     *
     * @param p
     * @return Classroom object
     */
    public Classroom searchClass(Predicate<Classroom> p) {
        for (Classroom cl : classrooms) {
            if (p.test(cl)) {
                return cl;
            }
        }
        return null;
    }
//  -----------------------------------------------------------
    public void addClasses(Classroom cls) {
        classrooms.add(cls);
    }
//  -----------------------------------------------------------
    /**
     * Use to show all classrooms in a school. Will display Students, Lecturers, and course of each class room
     */
    public void showAll() {
        System.out.println("List of available classes:");
        for (Classroom classroom : classrooms) {
            classroom.display();
        }
        System.out.print("Courses:");
        System.out.println(this.courses.toString());
    }
//    -----------------------------------------------------------

    /**
     * Show a list of course in order Ex: 1. Math 2. English
     */
    public void showCourses() {
        if (!this.courses.isEmpty()) {
            System.out.println("Available courses:");
            for (int i = 0; i < this.courses.size(); i++) {
                System.out.println((i + 1) + ". " + this.courses.get(i));
            }
        } else {
            System.out.println("No courses are found, please create them first!");
        }
        System.out.println("-----------------------------");
    }
//    -----------------------------------------------------------

    /**
     * Show a list of classroom ID in order Ex: 1. Math 2. English
     */
    public void showClassrooms() {
        if (!this.classrooms.isEmpty()) {
            System.out.println("Available classrooms:");
            for (int i = 0; i < this.classrooms.size(); i++) {
                System.out.println((i + 1) + ". " + this.classrooms.get(i).getClassID());
            }
        } else {
            System.out.println("No classrooms are found, please create them first!");
        }

        System.out.println("-----------------------------");
    }
//    -----------------------------------------------------------

    public void addCourse(String s) {
        if (!s.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("One course at a time only!");
        } else if (courses.contains(s)) {
            System.out.println("Course already exists");
        } else {
            courses.add(s);
        }
    }
//    -----------------------------------------------------------
    /**
     * Use to check if a teacher has his timeline duplicated (Teach two class with the same timeline)
     * @param l The teacher to check for
     */
    public void checkLecturerTL(Lecturer l) {
        ArrayList<Lecturer> lList = new ArrayList<>();
        for (Classroom cl : this.classrooms) {
            for (Lecturer lecturer : cl.getlList()) {
                if (lecturer.getName().equals(l.getName()) && !lecturer.gettLine().isEmpty()) {
                    lList.add(lecturer);
                }
            }
        }
        System.out.println("Giao vien tim duoc: " + lList);
        System.out.println("Total: " +lList.size());
        Classroom.checkLecturerTL(lList);
    }
//    -----------------------------------------------------------
    /**
     * Class ID -> Student -> Lecturer
     *
     * @param filePath School.txt
     */
    public void loadDataTxt(String filePath) {
        try ( Scanner sc = new Scanner(new FileReader(filePath))) {
            String classID = null;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] sl = s.split(",");
                if (s.isEmpty()) {
                    continue;
                }
                if (s.matches("Class:[A-Za-z0-9]*.*") == true) {
                    classID = s.split(":")[1];
                } else if (sl[0].equals("S")) {
                    Classroom.addStd(sl[1], sl[2], sl[3]);
                } else if (sl[0].equals("L")) {
                    final String cID = classID;
                    Classroom cr = this.searchClass(p -> p.getClassID().equals(cID));
                    if (!this.courses.contains(sl[2])) {
                        this.courses.add(sl[2]);
                    }
                    cr.addLec(new Lecturer(sl[1], sl[2]));
                }
            }
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(School.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Wrong data format!");
        } catch (Exception e) {
            System.out.println("Wrong data format!");
            System.exit(0);
        }
    }
}
