package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import view.AppTools;
import view.ManageView;

public class Classroom {

    private ArrayList<Student> sList = new ArrayList();
    private ArrayList<Lecturer> lList = new ArrayList();
    private String classID;

    /**
     *
     * @return A list of Lecturer
     */
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
//-----------------------------------------------------------------------

    public Classroom() {
    }

    public Classroom(String classID) {
        this.classID = classID;
    }
//-----------------------------------------------------------------------

    public static void addStd(String sName, String sClassID, String sDob) {
        if (AppTools.checkName(sName)) {
            sDob = Student.formatDate(sDob);
            try {
                Student s = new Student(sName, sClassID, sDob);
                Classroom cr = ManageView.school.searchClass(p -> p.classID.equals(sClassID));
                if (cr == null) {
                    Classroom n = new Classroom(s.getClassID());
                    n.sList.add(s);
                    ManageView.school.addClasses(n);
//                    Debug
                } else {
                    cr.sList.add(s);
                }
            } catch (Exception e) {
                System.out.println("Wrong birthday!");
            }
        }
    }
//------------------------------------------------------------------------

    public void addLec(Lecturer l) {
        if (this.getlList().contains(l)) {
            System.out.println("This lecturer is already in the class");
        } else if (!Lecturer.checkValid(l)) {
            System.out.println("Lecturer name is not valid");
        } else {
            lList.add(l);
        }
    }
//------------------------------------------------------------------------

    public void display() {
        System.out.println("Class " + this.classID);
        System.out.println("Students of " + this.classID);
        for (Student student : sList) {
            System.out.println(student);
        }
        System.out.println("Total: " + this.sList.size());

        System.out.println("Taught by Lecturers: ");
        for (Lecturer l : lList) {
            System.out.println(l);
        }
        System.out.println("Total: " + this.sList.size() + " students, " + this.lList.size() + " lectures");
    }
//------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Class: " + classID;
    }
//------------------------------------------------------------------------

    /**
     * 1: 2 giao vien 1 lop trung gio nhau 
     * 2: 1 giao vien o nhieu lop trung gio nhau
     * @param lList a list of lecturer to be checked
     */
    public static void checkLecturerTL(ArrayList<Lecturer> lList) {
        System.out.println("Checking for error");
        boolean check = true;
//        TH1:
        ArrayList<Integer> totalTimeline = new ArrayList<>();
        for (Lecturer lecturer : lList) {
            totalTimeline.addAll(lecturer.gettLine());
        }
        totalTimeline.sort((t1, t2) -> t1 - t2);
        System.out.println(totalTimeline.toString());
        if (totalTimeline.size() > 1) {
            for (int i = 0; i < totalTimeline.size() - 1; i++) {
                if (Objects.equals(totalTimeline.get(i), totalTimeline.get(i + 1))) {
                    System.out.println("Duplicate found: " + totalTimeline.get(i));
                    check = false;
                    break;
                }
            }
        } else {
            System.out.println("No one to compare (Debug)");
        }
        
    }
}
