package console;

import model.Lecturer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SchoolLogs extends ArrayList<String> {
    public SchoolLogs() {
        super();
    }


    public String getTimeNow() {
        ZonedDateTime timeNow = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z").format(timeNow);
    }

    public void warn (String desc) {
        String s = "!!! " + desc + " !!!";
        this.add(s + ", at " + getTimeNow());
        System.out.println(s);
    }

    public void getLogsClassroom(String classID) {
        String s = String.format("-> Class %s created at %s",classID, getTimeNow());
        this.add(s);
        System.out.println(s);
    }
    public void getLogsStudent(String sName, String sClassID) {
        String s = String.format("-> Student %s, class %s added at %s",sName, sClassID, getTimeNow());
        this.add(s);
        System.out.println(s);
    }
    public void getLogsCourse (String name) {
        String s = "-> " + name + " added at " + getTimeNow();
        this.add(s);
        System.out.println(s);
    }
    public void getLogsLecturer (String name, String course, String classID) {
        String s = String.format("-> %s teacher %s added to %s at %s", course, name, classID,getTimeNow());
        this.add(s);
        System.out.println(s);
    }

    public void getLogsTimeline(Lecturer r) {
        String s = String.format("-> Teacher %s changed timeline to %s at %s", r.getName(), Lecturer.displayTLine(r),getTimeNow());
        this.add(s);
        System.out.println(s);
    }

    public void displayLog () {
        System.out.println("-------------Logs------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i+1 + ". " + this.get(i));
        }
        System.out.println("-----------------------------");
    }
}
