package org.byovsiannikov.student.services;

import org.byovsiannikov.student.converter.StudentConverter;
import org.byovsiannikov.student.dto.Student;
import org.byovsiannikov.student.entity.StudentEntity;
import org.byovsiannikov.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentConverter studentConverter;

    private Student studentDto;
    private StudentEntity studentEntity;

    @BeforeEach
    void setUp() {
        studentDto = Student.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();

        studentEntity = StudentEntity.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();
    }

    @Test
    void saveStudent() {
        when(studentConverter.fromDTO(any(Student.class))).thenReturn(studentEntity);
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(studentEntity);

        studentService.saveStudent(studentDto);

        verify(studentRepository, times(1)).save(any(StudentEntity.class));
    }

    @Test
    void getAllStudents() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        studentEntities.add(studentEntity);
        List<Student> studentDtos = new ArrayList<>();
        studentDtos.add(studentDto);

        when(studentRepository.findAll()).thenReturn(studentEntities);
        when(studentConverter.toDTO(studentEntities)).thenReturn(studentDtos);

        List<Student> result = studentService.getAllStudents();

        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
        verify(studentConverter, times(1)).toDTO(studentEntities);
    }

    @Test
    void findAllStudentsBySchool() {
        int schoolId = 1;
        List<StudentEntity> studentEntities = new ArrayList<>();
        studentEntities.add(studentEntity);
        List<Student> studentDtos = new ArrayList<>();
        studentDtos.add(studentDto);

        when(studentRepository.findAllBySchoolId(schoolId)).thenReturn(studentEntities);
        when(studentConverter.toDTO(studentEntities)).thenReturn(studentDtos);

        List<Student> result = studentService.findAllStudentsBySchool(schoolId);

        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAllBySchoolId(schoolId);
        verify(studentConverter, times(1)).toDTO(studentEntities);
    }
}
