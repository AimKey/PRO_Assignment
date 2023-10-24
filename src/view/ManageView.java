package view;

import java.util.ArrayList;
import model.Classroom;
import model.Lecturer;
import model.School;

public class ManageView extends Menu {

    public static School school = new School();

    public static void main(String[] args) {
        ManageView menu = new ManageView();
        menu.run();
    }
    static String[] mc = {"Add Student", "Add course", "Add lecturer", "Enroll course", "Timetable", "Search class", "Exit"};

    public ManageView() {
        super(mc, "Student Management");
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
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                System.out.println("See you next time!");
                System.exit(0);
            default:
                System.out.println("See you next time");
                System.exit(0);
        }
    }

    public void addStudent() {
        String sName = AppTools.getString("Name");
        String sDob = AppTools.getString("Dob (format: dd/MM/yyyy)");
        String sClassID = AppTools.getString("ClassID").toUpperCase();
        Classroom.addStd(sName, sClassID, sDob);
//        Debug
        school.showClasses();
    }

    public void addCourse() {
        String course = AppTools.getString("Course name");
        school.addCourse(course);
//        Debug
        school.showCourses();
    }

    public void addLecturer() {
        String lName = AppTools.getString("Lecturer name");
        
        school.showClasses();
    }
}
