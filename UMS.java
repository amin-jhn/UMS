import java.util.ArrayList;

public class UMS {

    ArrayList <Student> students = new ArrayList<>();

    public static class Course {
        String name;
        int code;
        String group;
        int unit;
        Master master;
        int totalCapacity;
        int remnantCapacity;
        ArrayList <Student> listOfStudents;
        String timeOfClass;
        int minimumUnitsNeeded;

        public Course(String name, int code, String group, int unit, Master master,
                      int totalCapacity, int remnantCapacity,
                      String timeOfClass, int minimumUnitsNeeded) {

            this.name = name;
            this.code = code;
            this.group = group;
            this.unit = unit;
            this.master = master;
            this.totalCapacity = totalCapacity;
            this.remnantCapacity = remnantCapacity;
            this.listOfStudents = new ArrayList<>();
            this.timeOfClass = timeOfClass;
            this.minimumUnitsNeeded = minimumUnitsNeeded;
        }

        public Course(){
        }

    }

    public static class Person {
        String firstN;
        String lastN;
        String phoneNumber;
        String birthdate;
        String nationalCode;

        public Person(String firstN, String lastN, String phoneNumber, String birthdate, String nationalCode) {
            this.firstN = firstN;
            this.lastN = lastN;
            this.phoneNumber = phoneNumber;
            this.birthdate = birthdate;
            this.nationalCode = nationalCode;
        }

        public Person(){

        }
    }


    public static class OperableCourse extends Course {
        String placeOfClass;
        int totalReports;
        int minimumReports;


        public OperableCourse(String name, int code, String group,
                              int unit, Master master, int totalCapacity,
                              int remnantCapacity, String timeOfClass, int minimumUnitsNeeded,
                              String placeOfClass, int totalReports, int minimumReports) {

            super(name, code, group, unit, master, totalCapacity, remnantCapacity, timeOfClass, minimumUnitsNeeded);
            this.placeOfClass = placeOfClass;
            this.totalReports = totalReports;
            this.minimumReports = minimumReports;
        }

        public OperableCourse(){
            super();
        }
    }


    public static class GeneralCourse extends Course {
        String midtermExamTime;
        String finalExamTime;
        int totalNumOfPractices;
        int minPracticesNeeded;

        public GeneralCourse(String name, int code, String group,
                             int unit, Master master, int totalCapacity,
                             int remnantCapacity, String timeOfClass, int minimumUnitsNeeded,
                             String midtermExamTime, String finalExamTime,
                             int totalNumOfPractices, int minPracticesNeeded) {

            super(name, code, group, unit, master, totalCapacity, remnantCapacity, timeOfClass, minimumUnitsNeeded);
            this.midtermExamTime = midtermExamTime;
            this.finalExamTime = finalExamTime;
            this.totalNumOfPractices = totalNumOfPractices;
            this.minPracticesNeeded = minPracticesNeeded;
        }

        public GeneralCourse(){
            super();
        }
    }


    public static class SpecializedCourse extends Course {
        String midtermExamTime;
        String finalExamTime;
        int totalNumOfPractices;
        int minPracticesNeeded;
        String projectDeadline;
        int minProjectScore;

        public SpecializedCourse(String name, int code, String group,
                                 int unit, Master master, int totalCapacity,
                                 int remnantCapacity, String timeOfClass, int minimumUnitsNeeded,
                                 String midtermExamTime, String finalExamTime,
                                 int totalNumOfPractices, int minPracticesNeeded,
                                 String projectDeadline, int minProjectScore) {

            super(name, code, group, unit, master, totalCapacity, remnantCapacity, timeOfClass, minimumUnitsNeeded);
            this.midtermExamTime = midtermExamTime;
            this.finalExamTime = finalExamTime;
            this.totalNumOfPractices = totalNumOfPractices;
            this.minPracticesNeeded = minPracticesNeeded;
            this.projectDeadline = projectDeadline;
            this.minProjectScore = minProjectScore;
        }

        public SpecializedCourse(){
            super();
        }
    }


    public static class Master extends Person {
        long masterCode;
        String college;
        String daysOfAttendance;
        String listOfCourses;

        public Master(String firstN, String lastN, String phoneNumber,
                      String birthdate, String nationalCode, long masterCode,
                      String college, String daysOfAttendance, String listOfCourses) {

            super(firstN, lastN, phoneNumber, birthdate, nationalCode);
            this.masterCode = masterCode;
            this.college = college;
            this.daysOfAttendance = daysOfAttendance;
            this.listOfCourses = listOfCourses;
        }

        public Master() {
            super();
        }
    }


    public static class Student extends Person {
        long stuCode;
        int termsPassed;
        int unitsPassed;
        ArrayList <Course> StuCourses;
        int maxSpecializedCourse;
        int maxGeneralCourse;
        int maxOperableCourse;
        int unitsAdapted;

        public Student(String firstN, String lastN, String phoneNumber,
                       String birthdate, String nationalCode, long stuCode,
                       int termsPassed, int unitsPassed) {

            super(firstN, lastN, phoneNumber, birthdate, nationalCode);
            this.stuCode = stuCode;
            this.termsPassed = termsPassed;
            this.unitsPassed = unitsPassed;
            StuCourses = new ArrayList<>();
            maxOperableCourse = 1;
            maxGeneralCourse = 6;
            maxSpecializedCourse = 2;
            unitsAdapted = 0;
        }

        public Student() {
            super();
            maxOperableCourse = 1;
            maxGeneralCourse = 6;
            maxSpecializedCourse = 2;
            unitsAdapted = 0;
        }

        public void pickCourse(Course course) {
            if (course instanceof SpecializedCourse && maxSpecializedCourse != 0 &&
                    checkCourseQualifications(course) && checkMaxUnit(course)){
                this.StuCourses.add(course);
                maxSpecializedCourse--;
                this.unitsAdapted += course.unit;
            }
            else if (course instanceof OperableCourse && maxOperableCourse != 0 &&
                    checkCourseQualifications(course) && checkMaxUnit(course)){
                this.StuCourses.add(course);
                maxOperableCourse--;
                this.unitsAdapted += course.unit;
            }
            else if (course instanceof GeneralCourse && maxGeneralCourse != 0 &&
                    checkCourseQualifications(course) && checkMaxUnit(course)){
                this.StuCourses.add(course);
                maxGeneralCourse--;
                this.unitsAdapted += course.unit;
            }
            else System.out.println("Maximum limit reached! Choose another course.");
        }

        public boolean checkCourseQualifications(Course course) {
            return course.minimumUnitsNeeded <= this.unitsPassed;
        }

        public boolean checkMaxUnit(Course course) {
            return  (unitsAdapted == 22 || unitsAdapted + course.unit > 22);
        }
    }
}