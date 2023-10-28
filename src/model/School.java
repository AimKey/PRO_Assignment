
package model;

import java.util.ArrayList;
import java.util.function.Predicate;
import static view.ManageView.school;

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

    public Classroom findClass(String findID) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassID().equals(findID)) {
                return classroom;
            }
        }
        return null;
    }

    public Classroom searchClass(Predicate<Classroom> p) {
        for (Classroom cl : classrooms) {
            if (p.test(cl)) {
                return cl;
            }
        }
        return null;
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
//    -----------------------------

    public void showCourses() {
        System.out.println("Available courses:");
        for (String c : courses) {
            System.out.print(c + " ");
        }
        System.out.println("\n-----------------------------");
    }
//    -----------------------------

    public void addCourse(String s) {
        if (!s.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("One course at a time only!");
        } else if (courses.contains(s)) {
            System.out.println("Course already exists");
        } else {
            courses.add(s);
        }
    }
//    -----------------------------
//    Experimental
    public ArrayList<String> getClassIDs() {
        ArrayList<String> n = new ArrayList();
        for (Classroom classroom : classrooms) {
            n.add(classroom.getClassID());
        }
        return n;
    }

    //    -----------------------------
    public String[] getCourseList() {
        String[] clrString = new String[this.courses.size()];
        for (int i = 0; i < this.courses.size(); i++) {
            clrString[i] = school.getCourses().get(i);
        }
        return clrString;
    }
}
