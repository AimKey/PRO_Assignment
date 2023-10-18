package view;

import model.Classroom;
import model.School;
import model.Student;

public class ManagementView extends Menu {

    School school = new School();

    public static void main(String[] args) {
        ManagementView menu = new ManagementView();
        menu.run();
    }
    static String[] mc = {"Add Student", "Add course", "Add lecturer", "Enroll course", "Timetable", "Search class", "Exit"};

    public ManagementView() {
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
                break;
            case 3:
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
        }
    }

    public void addStudent() {
//        Date of birth must in this format: dd/MM/yyyy
        System.out.println("Student info: ");
        String sName = AppTools.getString("Name");
        String sDob = AppTools.getString("Dob (format: dd/MM/yyyy)");
        String sClassID = AppTools.getString("ClassID").toUpperCase();
        try {
            Student s = new Student(sName, sClassID, sDob);
            Classroom cr = school.findClass(s.getClassID());
            if (cr == null) {
//                Tao class moi
                cr = new Classroom(s.getClassID());
                cr.addStd(s);
                school.addClasses(cr);
//                Debug
            } else {
//                Them vao class dang co (cr != null)
//                Update = cach xoa array cu va them array moi
                cr.addStd(s, school);
            }
            school.showClasses();

        } catch (Exception e) {
            System.out.println("Tao that bai!\nError: " + e);
        }
    }
}
