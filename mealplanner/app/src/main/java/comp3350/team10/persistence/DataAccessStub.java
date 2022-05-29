package comp3350.team10.persistence;

import comp3350.team10.application.Main;
import comp3350.team10.objects.*;

import java.util.ArrayList;
import java.util.List;


public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<DiaryItem> currentFoodLog;
    //private ArrayList<Course> courses;
    //private ArrayList<SC> scs;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String dbName) {
        DiaryItem mealEntry;
        //Course course;
        //SC mySC;

        currentFoodLog = new ArrayList<DiaryItem>();
        mealEntry = new DiaryItem(100, ListItem.FragmentType.diaryEntry, "Banana", 100, ListItem.Unit.g, 50, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(101, ListItem.FragmentType.diaryEntry, "Salad", 50, ListItem.Unit.g, 50, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(102, ListItem.FragmentType.diaryEntry, "Hamburglar", 700, ListItem.Unit.g, 400, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(103, ListItem.FragmentType.diaryEntry, "Notfries", 500, ListItem.Unit.g, 30, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(104, ListItem.FragmentType.diaryEntry, "Banana", 100, ListItem.Unit.g, 50, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(105, ListItem.FragmentType.diaryEntry, "Salad", 50, ListItem.Unit.g, 50, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(106, ListItem.FragmentType.diaryEntry, "Hamburglar", 700, ListItem.Unit.g, 400, "myIcon");
        currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(107, ListItem.FragmentType.diaryEntry, "Notfries", 500, ListItem.Unit.g, 30, "myIcon");
        currentFoodLog.add(mealEntry);
        //mealEntry = new DiaryItem(103, ListItem.FragmentType.diaryModify, "Notfries", 500, ListItem.Unit.g, 30, "myIcon");
        //currentFoodLog.add(mealEntry);
        mealEntry = new DiaryItem(108, ListItem.FragmentType.diaryAdd, "Notfries", 500, ListItem.Unit.g, 30, "myIcon");
        currentFoodLog.add(mealEntry);

//        courses = new ArrayList<Course>();
//        course = new Course("COMP3010", "Distributed Computing");
//        courses.add(course);
//        course = new Course("COMP3020", "Human-Computer Interaction");
//        courses.add(course);
//        course = new Course("COMP3350", "Software Development");
//        courses.add(course);
//        course = new Course("COMP3380", "Databases");
//        courses.add(course);
//
//        scs = new ArrayList<SC>();
//        mySC = new SC("100", "COMP3010", "Gary Chalmers", "Distributed Computing", "C+");
//        scs.add(mySC);
//        mySC = new SC("200", "COMP3010", "Selma Bouvier", "Distributed Computing", "A+");
//        scs.add(mySC);
//        mySC = new SC("100", "COMP3350", "Gary Chalmers", "Software Development", "A");
//        scs.add(mySC);
//        mySC = new SC("300", "COMP3350", "Arnie Pye", "Software Development", "B");
//        scs.add(mySC);
//        mySC = new SC("100", "COMP3380", "Gary Chalmers", "Databases", "A");
//        scs.add(mySC);
//        mySC = new SC("200", "COMP3380", "Selma Bouvier", "Databases", "B");
//        scs.add(mySC);

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public ArrayList<DiaryItem> getToday() {
        return currentFoodLog;
    }
//
//    public ArrayList<Student> getStudentRandom(Student currentStudent)
//    {
//        ArrayList<Student> newStudents;
//        int index;
//
//        newStudents = new ArrayList<Student>();
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            newStudents.add(students.get(index));
//        }
//        return newStudents;
//    }
//
//    public String insertStudent(Student currentStudent)
//    {
//        // don't bother checking for duplicates
//        students.add(currentStudent);
//        return null;
//    }
//
//    public String updateStudent(Student currentStudent)
//    {
//        int index;
//
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            students.set(index, currentStudent);
//        }
//        return null;
//    }
//
//    public String deleteStudent(Student currentStudent)
//    {
//        int index;
//
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            students.remove(index);
//        }
//        return null;
//    }
//
//    public String getCourseSequential(List<Course> courseResult)
//    {
//        courseResult.addAll(courses);
//        return null;
//    }
//
//    public ArrayList<Course> getCourseRandom(Course currentCourse)
//    {
//        ArrayList<Course> newCourses;
//        int index;
//
//        newCourses = new ArrayList<Course>();
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            newCourses.add(courses.get(index));
//        }
//        return newCourses;
//    }
//
//    public String insertCourse(Course currentCourse)
//    {
//        // don't bother checking for duplicates
//        courses.add(currentCourse);
//        return null;
//    }
//
//    public String updateCourse(Course currentCourse)
//    {
//        int index;
//
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            courses.set(index, currentCourse);
//        }
//        return null;
//    }
//
//    public String deleteCourse(Course currentCourse)
//    {
//        int index;
//
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            courses.remove(index);
//        }
//        return null;
//    }
//
//    public ArrayList<SC> getSC(SC currentSC)
//    {
//        ArrayList<SC> newSCs;
//        SC sc;
//        int counter;
//
//        // get the SC objects with the same studentID as currentSC
//        newSCs = new ArrayList<SC>();
//        for (counter=0; counter<scs.size(); counter++)
//        {
//            sc = scs.get(counter);
//            if (sc.getStudentID().equals(currentSC.getStudentID()))
//            {
//                newSCs.add(scs.get(counter));
//            }
//        }
//        return newSCs;
//    }
//
//    public ArrayList<SC> getCS(SC currentSC)
//    {
//        ArrayList<SC> newSCs;
//        SC cs;
//        int counter;
//
//        // get the SC objects with the same courseID as currentSC
//        newSCs = new ArrayList<SC>();
//        for (counter=0; counter<scs.size(); counter++)
//        {
//            cs = scs.get(counter);
//            if (cs.getCourseID().equals(currentSC.getCourseID()))
//            {
//                newSCs.add(scs.get(counter));
//            }
//        }
//        return newSCs;
//    }
}
