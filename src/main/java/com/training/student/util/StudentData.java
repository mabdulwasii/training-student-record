package com.training.student.util;

import com.training.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private StudentData() {
    }

    public static List<Student> STUDENT_LIST = new ArrayList<>();


    static {
        STUDENT_LIST.add(new Student(1, 12, "Ade"));
        STUDENT_LIST.add(new Student(2, 4, "Ola"));
        STUDENT_LIST.add(new Student(3, 20, "Wale"));
    }


}
