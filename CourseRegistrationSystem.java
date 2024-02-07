import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void decrementAvailableSlots() {
        if (availableSlots > 0) {
            availableSlots--;
        } else {
            System.out.println("No available slots for this course.");
        }
    }

    public void incrementAvailableSlots() {
        if (availableSlots < capacity) {
            availableSlots++;
        } else {
            System.out.println("Course already at maximum capacity.");
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
               "\nCapacity: " + capacity + "\nAvailable Slots: " + availableSlots + "\nSchedule: " + schedule;
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
        } else {
            if (course.getAvailableSlots() > 0) {
                registeredCourses.add(course);
                course.decrementAvailableSlots();
                System.out.println("Registration successful for course: " + course.getCourseCode());
            } else {
                System.out.println("No available slots for this course.");
            }
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.incrementAvailableSlots();
            System.out.println("Dropped course: " + course.getCourseCode());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size();
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Basic computer science concepts", 30, "MWF 10:00 AM");
        Course c2 = new Course("MATH201", "Calculus I", "Introduction to calculus", 25, "TTH 2:00 PM");

        Student s1 = new Student(1, "John Doe");
        Student s2 = new Student(2, "Jane Doe");

        // Student 1 registers for two courses
        s1.registerForCourse(c1);
        s1.registerForCourse(c2);

        // Student 2 registers for one course
        s2.registerForCourse(c1);

        // Display course information
        System.out.println("\nCourse Information:");
        System.out.println("Course 1:\n" + c1 + "\n");
        System.out.println("Course 2:\n" + c2);

        // Display student information
        System.out.println("\nStudent Information:");
        System.out.println("Student 1:\n" + s1 + "\n");
        System.out.println("Student 2:\n" + s2);

        // Student 1 drops a course
        s1.dropCourse(c1);

        // Display updated information
        System.out.println("\nAfter Dropping a Course:");
        System.out.println("Course Information:\n" + c1 + "\n");
        System.out.println("Student Information:\n" + s1);
    }
}
