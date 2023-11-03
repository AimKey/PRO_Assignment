package model;

import java.util.ArrayList;
import java.util.Objects;
import console.AppTools;
import studentManagement.StudentManagement;

import static studentManagement.StudentManagement.logs;

public class Classroom {

    private ArrayList<Student> sList = new ArrayList<>();
    private ArrayList<Lecturer> lList = new ArrayList<>();
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
                Classroom cr = StudentManagement.school.searchClass(p -> p.classID.equals(sClassID));
                if (cr == null) {
                    Classroom n = new Classroom(s.getClassID());
                    n.sList.add(s);
                    StudentManagement.school.addClasses(n);
//                    Logs here
                    logs.getLogsClassroom(sClassID);
                    logs.getLogsStudent(sName, sClassID);

                } else {
//                    Logs here
                    cr.sList.add(s);
                    logs.getLogsStudent(sName, sClassID);
                }
            } catch (Exception e) {
                logs.warn("Wrong " +sName +" birthday");
            }
        }
    }
//------------------------------------------------------------------------

    public void addLec(Lecturer l) {
        if (this.getlList().contains(l)) {
            logs.warn("This lecturer is already in the class");
        } else if (!Lecturer.checkValid(l)) {
            logs.warn("Lecturer name is not valid");
        } else {
            lList.add(l);
            logs.getLogsLecturer(l.getName(),l.getCourse(),this.getClassID());
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
        System.out.println("-----------------------------");
    }
//------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Class:" + classID;
    }
//------------------------------------------------------------------------
    /**
     * Ham nay se kiem tra xem cac Lecturer trong lList co trung gio nhau ko
     * 2 truong hop trung gio co the xay ra:
     * 1. Trung gio giua cac giao vien trong 1 lop
     * 2. Trung gio cua 1 giao vien trong nhieu lop
     * @param lList a list of lecturer to be checked
     */
    public static boolean checkLecturerTL(ArrayList<Lecturer> lList) {
        ArrayList<Integer> totalTimeline = new ArrayList<>();
        for (Lecturer lecturer : lList) {
            totalTimeline.addAll(lecturer.gettLine());
        }
        totalTimeline.sort((t1, t2) -> t1 - t2);
        if (totalTimeline.size() > 1) {
            for (int i = 0; i < totalTimeline.size() - 1; i++) {
                if (Objects.equals(totalTimeline.get(i), totalTimeline.get(i + 1))) return false;
            }
        }
        return true;
    }
}
