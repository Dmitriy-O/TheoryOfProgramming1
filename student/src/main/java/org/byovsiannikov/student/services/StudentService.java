package org.byovsiannikov.student.services;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.student.Student;
import org.byovsiannikov.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void saveStudent (Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findAllStudentsBySchool (int schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }
}
