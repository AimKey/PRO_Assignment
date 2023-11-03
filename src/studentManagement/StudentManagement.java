package studentManagement;

import console.AppTools;
import console.Menu;
import console.SchoolLogs;
import model.Classroom;
import model.Lecturer;
import model.School;

import java.io.File;
import java.util.ArrayList;

public class StudentManagement extends Menu {

    public static School school = new School();
    public static SchoolLogs logs = new SchoolLogs();
    public static void main(String[] args) {
        try {
            StudentManagement menu = new StudentManagement("School.txt");
            menu.run();
        } catch (Exception e) {
            logs.warn("Wrong input");
        }
    }

    static String[] mc = {"Add Student", "Add course", "Add lecturer", "Course enroll", "Timetable", "Search class",
            "Show logs", "Exit"};
    public StudentManagement(String filePath) {
        super(mc, "Student Management");
        if (new File(filePath).exists()) {
            school.loadDataTxt(filePath);
        }
    }

    //--------------------------------------------
    @Override
    public void execute(int n) {
        // Debug
        switch (n) {
            case 1:
                addStudent();
                break;
            case 2:
                addCourse();
                break;
            case 3:
                addLecturer();
                break;
            case 4:
                setupCourse();
                break;
            case 5:
                showTimetable();
                break;
            case 6:
                break;
            case 7:
                doDebug();
                break;
            case 8:
                school.doSave("school.txt");
                System.out.println("See you next time!");
                System.exit(0);
        }
    }
//-----------------------------------------------------------------------
    public void addStudent() {
        String sName = AppTools.getString("Name");
        String sDob = AppTools.getString("Dob (format: dd/MM/yyyy)");
        String sClassID = AppTools.getString("ClassID").toUpperCase();
//        Logs go to addStd
        Classroom.addStd(sName, sClassID, sDob);
    }
//-----------------------------------------------------------------------

    public void addCourse() {
        String course = AppTools.getString("Course name");
        school.addCourse(course);
//        Logs go here
        logs.getLogsCourse(course);
    }
//-----------------------------------------------------------------------

    public void addLecturer() {
        try {
            String lName = AppTools.getString("Lecturer name");
            boolean check = AppTools.show(school.getCourses(), "courses");
            if (!check) {
                throw new Exception();
            }
            String lCourse = school.getCourses().get(AppTools.getInt("Choose course") - 1);
            Lecturer s = new Lecturer(lName, lCourse);
            ArrayList<Classroom> crs = school.getClassrooms();
            do {
                AppTools.show(school.getClassrooms(), "classrooms");
                Classroom cr = crs.get(AppTools.getInt("Choose teacher class: ") - 1);
                cr.addLec(s);

            } while (AppTools.getString("Assign to another class? (y/n)").equals("y"));
        } catch (Exception e) {
            logs.warn("No classes / courses are found, please create them first");
        }
    }

    //----------------------------------------------------
    public void setupCourse() {
        Menu classMenu = new Menu(school.getClassrooms(), "Setup course - classes") {
            @Override
            public void execute(int n) {
                Classroom cr = school.getClassrooms().get(n - 1);
                Menu lecMenu = new Menu(cr.getlList(), "Setup course - Lectures of " + cr.getClassID()) {
                    @Override
                    public void execute(int n) {
                        Lecturer r = cr.getlList().get(n - 1);
                        r.setupTimeline();
                        boolean check = school.checkLecturerTL(r, cr.getClassID());
                        if (!check)
                            logs.warn("2 or more lecturers have the same timetable");
                    }
                };
                lecMenu.run();
            }
        };
        classMenu.run();
    }

    //----------------------------------------------------
    public void showTimetable() {
        Menu chooseCl = new Menu(school.getClassrooms(), "Choose class timetable") {
            @Override
            public void execute(int n) {
                String[][] timetable = new String[4 + 1][6 + 1];
                Classroom cr = school.getClassrooms().get(n - 1);
                ArrayList<Lecturer> lecturers = cr.getlList();
                for (Lecturer l : lecturers) {
                    for (int item : l.gettLine()) {
                        int[] pos = Lecturer.calTLine(item);
                        timetable[pos[0]][pos[1]] = l.getCourse();
                    }
                }
//                Logs go here
                AppTools.printArr(timetable, cr.getClassID());
            }
        };
        chooseCl.run();
    }

    //----------------------------------------------------
    public void doDebug() {
//        school.showAll();
        logs.displayLog();
    }
//----------------------------------------------------
}
