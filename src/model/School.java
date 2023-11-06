package model;

import view_controller.StudentManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class School {

    private ArrayList<Classroom> classrooms = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();
    public static String[] weekdays = {"", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    public static int numberOfSlots = (weekdays.length - 1) * 4;

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

    //  -----------------------------------------------------------
    public Classroom searchClass(Predicate<Classroom> p) {
        for (Classroom cl : classrooms) {
            if (p.test(cl)) {
                return cl;
            }
        }
        return null;
    }

    //  -----------------------------------------------------------
    public void showAll() {
        System.out.println("List of available classes:");
        for (Classroom classroom : classrooms) {
            classroom.display();
        }
        System.out.print("Courses:");
        System.out.println(this.courses.toString());
        System.out.println("-----------------------------");

    }

    //    -----------------------------------------------------------
    public void addCourse(String s) {
        if (!s.matches("^[a-zA-Z0-9]+$")) {
            StudentManagement.logs.warn("One course at a time only");
        } else if (courses.contains(s)) {
            StudentManagement.logs.warn("Course already exists");
        } else {
            courses.add(s);
            StudentManagement.logs.getLogsCourse(s);
        }
    }
//    -----------------------------------------------------------

    /**
     * Use to check if a teacher has his timeline duplicated (Teach two class with the same timeline)
     *
     * @param l The teacher to check for
     */
    public boolean checkLecturerTL(Lecturer l, String lClassID) {
        ArrayList<Lecturer> lList = new ArrayList<>();
        for (Classroom cl : this.classrooms) {
            for (Lecturer lecturer : cl.getlList()) {
                if (lecturer.getName().equals(l.getName()) && !lecturer.gettLine().isEmpty()) {
                    lList.add(lecturer);
                }
            }
        }
        boolean check1 = Classroom.checkLecturerTL(this.searchClass(p -> p.getClassID().equals(lClassID)).getlList());
        boolean check2 = Classroom.checkLecturerTL(lList);
        return check1 && check2;
    }
//    -----------------------------------------------------------

    /**
     * Class ID -> Student -> Lecturer
     *
     * @param filePath School.txt
     */
    public void loadDataTxt(String filePath) {
        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            String classID = null;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] sl = s.split(",");
                if (s.isEmpty()) continue;

                if (s.matches("Class:[A-Za-z0-9]*.*")) {
                    classID = s.split(":")[1];

                } else if (sl[0].equals("S")) {
                    Classroom.addStd(sl[1], sl[2], sl[3]);

                } else if (sl[0].equals("L")) {
                    final String cID = classID;
                    Classroom cr = this.searchClass(p -> p.getClassID().equals(cID));

                    if (!this.courses.contains(sl[2])) {
                        this.courses.add(sl[2]);
                        StudentManagement.logs.getLogsCourse(sl[2]);
                    }
//                    Handle convert timeline to int array
                    ArrayList<Integer> timeLine = new ArrayList<>();
                    if (sl.length == 4) {
                        String[] temp = sl[3].split("-");
                        for (String t : temp) {
                            timeLine.add(Integer.parseInt(t));
                        }
                    }
                    cr.addLec(new Lecturer(sl[1], sl[2], timeLine));
                }
            }
        } catch (FileNotFoundException ex) {
            StudentManagement.logs.warn("File not found, creating new...");
        } catch (Exception e) {
            StudentManagement.logs.warn("Wrong data format!");
        }
    }

    //    -----------------------------------------------------------
    public void saveDataTxt(String filePath) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath))) {
            for (Classroom cl : this.classrooms) {
                br.write(cl.toString() + "\n");
                for (Student s : cl.getsList()) {
                    br.write(String.format("S,%s,%s,%s\n", s.getName(), s.getClassID(), s.getDob()));
                }
                for (Lecturer l : cl.getlList()) {
                    br.write(String.format("L,%s,%s,%s\n",
                            l.getName(), l.getCourse(), handleTimeline(l.gettLine())));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String handleTimeline(ArrayList<Integer> tl) {
        String[] s = new String[tl.size()];
        for (int i = 0; i < tl.size(); i++) {
            s[i] = String.valueOf(tl.get(i));
        }
        return String.join("-", s);
    }
//    -----------------------------------------------------------
}
