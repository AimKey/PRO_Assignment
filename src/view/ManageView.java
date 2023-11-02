package view;

import model.Classroom;
import model.Lecturer;
import model.School;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ManageView extends Menu {

    public static School school = new School();

    public static void main(String[] args) {
        ManageView menu = new ManageView("School.txt");
        menu.run();
    }

    static String[] mc = {"Add Student", "Add course", "Add lecturer", "Course enroll", "Timetable", "Search class", "Show all (debug)", "Exit"};

    public ManageView() {
        super(mc, "Student Management");
    }

    //    Student -> Teacher -> Course
    public ManageView(String filePath) {
        super(mc, "Student Management");
        if (new File(filePath).exists()) {
            System.out.println("Loading " + filePath);
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
//                Delete this when you are done
                doDebug();
                break;
            case 8:
                System.out.println("See you next time!");
                System.exit(0);
        }
    }

    //-----------------------------------------------------------------------
    public void addStudent() {
        String sName = AppTools.getString("Name");
        String sDob = AppTools.getString("Dob (format: dd/MM/yyyy)");
        String sClassID = AppTools.getString("ClassID").toUpperCase();
        Classroom.addStd(sName, sClassID, sDob);
//        Debug
        school.showAll();
    }
//-----------------------------------------------------------------------

    public void addCourse() {
        String course = AppTools.getString("Course name");
        school.addCourse(course);
//        Debug
        school.showCourses();
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
            System.out.println(s);
            school.showAll();
        } catch (Exception e) {
            System.out.println("No classes / courses are found, please create them first!");
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
                        System.out.println(r.displayTLine());
                        school.checkLecturerTL(r);
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
                        int[] pos = l.calTLine(item);
                        System.out.println(Arrays.toString(pos));
                        timetable[pos[0]][pos[1]] = l.getCourse();
                    }
                }
                AppTools.printArr(timetable);
            }
        };
        chooseCl.run();
    }

    //----------------------------------------------------
    public void doDebug() {
        school.showAll();
    }
//----------------------------------------------------
}
