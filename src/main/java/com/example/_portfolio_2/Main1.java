package com.example._portfolio_2;
import java.util.*;
    public class Main1 {
        // this little program uses the Model class from portfolio 2 and prints
        // out insert statements that can be copied into the SQL console for
        // inserting data into the database. Place Model.java in the same directory
        // as this program
        // The insert statement assumes four columns in a table activity:
        // courseno: integer
        // ects : integer
        // name : name of activity
        // prg : base program or subject mosdule
        // You can adapt the "showActivity' method so it fits your E/R diagram
        Model m=new Model();
        int num=10;
        public static void main(String[] args) {
            Main1 obj = new Main1();
            Model m = new Model();
            for (String prg : m.baseProgram()) {
                for (String s : m.baseCourse(prg))
                    obj.showActivity(prg, s);
                for (String s : m.baseProject(prg))
                    obj.showActivity(prg, s);
            }
            for (String prg : m.subjectModule()){
                for (String s : m.subjectCourse(prg))
                    obj.showActivity(prg, s);
                for (String s : m.subjectProject(prg))
                obj.showActivity(prg,s);
            }
        }
        void showActivity(String prg, String s) {
            int ects=m.courseWeight(s);
            num++;
            System.out.println("insert into course values(" +
                    num + "," + ects + ",'" + s + "','" + prg + "');");
        }
    }

