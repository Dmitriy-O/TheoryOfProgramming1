package org.byovsiannikov.student.services;

import org.byovsiannikov.student.dto.Student;

import java.util.List;

public interface StudentService {

    void saveStudent (Student student);

    List<Student> getAllStudents ();

    List<Student> findAllStudentsBySchool (int id);
}
