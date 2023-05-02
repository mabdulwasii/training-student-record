package com.training.student.util;

import com.training.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    public static List<Student> populate() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, 12, "Ade"));
        students.add(new Student(2, 4, "Ola"));
        students.add(new Student(3, 20, "Wale"));
        return students;
    }
}
