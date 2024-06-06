package org.byovsiannikov.student.services;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.configserver.logger.behavoral.LoggerDefault;
import org.byovsiannikov.configserver.logger.behavoral.LoggerStudent;
import org.byovsiannikov.configserver.logger.creational.LoggerSingleton;
import org.byovsiannikov.student.converter.StudentConverter;
import org.byovsiannikov.student.dto.Student;
import org.byovsiannikov.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentConverter studentConverter;

    public void saveStudent (Student student) {
        studentRepository.save(studentConverter.fromDTO(student));
        LoggerSingleton.getInstance().setLoggerStrategy(new LoggerStudent(new LoggerDefault())).log("saved");
    }

    public List<Student> getAllStudents () {
        return studentConverter.toDTO(studentRepository.findAll());
    }

    public List<Student> findAllStudentsBySchool (int schoolId) {
        return studentConverter.toDTO(studentRepository.findAllBySchoolId(schoolId));
    }
}
