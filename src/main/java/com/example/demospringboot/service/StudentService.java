package com.example.demospringboot.service;

import com.example.demospringboot.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void addStudent(Student student);
    void deleteStudentById(int id);
    void updateStudent(Student student);
}
