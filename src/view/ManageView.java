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
        ArrayList<String> clr = new ArrayList(school.getClassIDs());
        Menu classSelect = new Menu(clr, "Available classes") {
            @Override
            public void execute(int classChoice) {
                //            Chon lop
                Classroom cr = school.getClassrooms().get(classChoice - 1);
                ArrayList<String> courses = school.getCourses();

                Menu coursesSelect = new Menu(courses, "Available courses") {

                    public void run() {
                        while (true) {
                            int n = getSelected();
                            if (n > this.getmChon().size() || n <= 0) {break;}
                            if (execute(n, 0) == 0) {break;}
                        }
                    }
                    @Override
                    public  void execute(int n) {};

                    public int execute(int courseChoice, int temp) {
                        String cChoice = school.getCourses().get(courseChoice - 1);
                        Lecturer l = new Lecturer(lName, cChoice, cr.getClassID());
                        cr.addLec(l);
                        System.out.println("Lecturer added: " + l);
                        return 0;
                    }
                };
                coursesSelect.run();
                clr.remove(cr.getClassID());
            }
        };
        classSelect.run();
        school.showClasses();
    }
}
