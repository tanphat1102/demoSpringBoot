package com.example.demospringboot.DAO;


import com.example.demospringboot.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demoSpring";

    private static final String USER = "root";

    private static final String PASSWORD = "12345";


    // Hàm để lấy tất cả sinh viên từ database

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String SELECT_ALL_STUDENTS = "SELECT id, name, age, major FROM students";


        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);

             ResultSet resultSet = preparedStatement.executeQuery()) {


            while (resultSet.next()) {

                int id = resultSet.getInt("id");

                String name = resultSet.getString("name");

                int age = resultSet.getInt("age");

                String major = resultSet.getString("major");


                Student student = new Student(id, name, age, major);

                students.add(student);

            }


        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return students;

    }


    // Hàm để thêm một sinh viên mới vào database

    public void addStudent(Student student) {

        String INSERT_STUDENT_SQL = "INSERT INTO students (name, age, major) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {


            preparedStatement.setString(1, student.getName());

            preparedStatement.setInt(2, student.getAge());

            preparedStatement.setString(3, student.getMajor());

            preparedStatement.executeUpdate();

            System.out.println("Student added successfully: " + student.getName());

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }


    // Hàm để cập nhật thông tin sinh viên

    public void updateStudent(Student student) {

        String UPDATE_STUDENT_SQL = "UPDATE students SET name = ?, age = ?, major = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {


            preparedStatement.setString(1, student.getName());

            preparedStatement.setInt(2, student.getAge());

            preparedStatement.setString(3, student.getMajor());

            preparedStatement.setInt(4, student.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Student updated successfully: " + student.getName());

            } else {

                System.out.println("No student found with ID: " + student.getId());

            }

        } catch (SQLException e) {

            System.out.println("Student update failed: " + student.getName());

        }

    }


    // Hàm để xóa sinh viên

    public void deleteStudent(int id) {

        String DELETE_STUDENT_SQL = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {


            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Student with ID " + id + " deleted successfully.");

            } else {

                System.out.println("No student found with ID: " + id);

            }

        } catch (SQLException e) {

            System.out.println("exception occurred while deleting student with ID: " + id);

        }

    }


    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = new Student();

        student.setAge(10);

        student.setMajor("Major");

        student.setName("Major");

        studentDAO.addStudent(student);

        for (var students : studentDAO.getAllStudents()) {

            System.out.println(students.getId() + " " + students.getName() + " " + students.getAge() + " " + students.getMajor());

        }

    }


}


