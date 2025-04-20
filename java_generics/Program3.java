import java.util.*;

abstract class CourseType {
    String courseName;
    public CourseType(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
    public abstract String getEvaluationType();
}

class ExamCourse extends CourseType {
    public ExamCourse(String courseName) {
        super(courseName);
    }
    public String getEvaluationType() {
        return "Exam-Based";
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse(String courseName) {
        super(courseName);
    }
    public String getEvaluationType() {
        return "Assignment-Based";
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse(String courseName) {
        super(courseName);
    }
    public String getEvaluationType() {
        return "Research-Based";
    }
}

class Course<T extends CourseType> {
    private T courseType;
    private String department;

    public Course(String department, T courseType) {
        this.department = department;
        this.courseType = courseType;
    }

    public T getCourseType() {
        return courseType;
    }

    public String getDepartment() {
        return department;
    }
}

public class Program3 {
    public static void displayCourses(List<? extends Course<? extends CourseType>> courses) {
        for (Course<? extends CourseType> c : courses) {
            System.out.println(c.getDepartment() + " - " + c.getCourseType().getCourseName() + " (" + c.getCourseType().getEvaluationType() + ")");
        }
    }

    public static void main(String[] args) {
        List<Course<? extends CourseType>> courseList = new ArrayList<>();

        courseList.add(new Course<>("Computer Science", new ExamCourse("Data Structures")));
        courseList.add(new Course<>("Mechanical", new AssignmentCourse("Thermodynamics")));
        courseList.add(new Course<>("Biotech", new ResearchCourse("Genetic Engineering")));

        displayCourses(courseList);
    }
}
