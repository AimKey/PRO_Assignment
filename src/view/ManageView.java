package view;

import java.io.File;
import java.util.ArrayList;
import model.Classroom;
import model.Lecturer;
import model.School;

public class ManageView extends Menu {
    
    public static School school = new School();
    private static String filePath = "School.txt";
    
    public static void main(String[] args) {
        ManageView menu = new ManageView(filePath);
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
            if (check == false) throw new Exception();
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
        AppTools.show(school.getClassrooms(), "classes");
        Classroom cr = school.getClassrooms().get(AppTools.getInt("Your choice") - 1);
        AppTools.show(cr.getlList(), "lecturer");
        Lecturer r = cr.getlList().get(AppTools.getInt("Your choice") - 1);
        r.setupTimeline();
        cr.checkDupTimeline();
    }
    
//----------------------------------------------------    
    public void doDebug() {
        school.showAll();
    }
}
