package org.byovsiannikov.student.converter;

import org.byovsiannikov.student.dto.Student;
import org.byovsiannikov.student.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentConverterTest {

    StudentConverter studentConverter = Mappers.getMapper(StudentConverter.class);
    @Test
    void testToDTO() {
        // Arrange
        StudentEntity studentEntity = StudentEntity.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();

        // Act
        Student student = studentConverter.toDTO(studentEntity);

        // Assert
        assertThat(student.getId()).isEqualTo(studentEntity.getId());
        assertThat(student.getFirstName()).isEqualTo(studentEntity.getFirstName());
        assertThat(student.getLastName()).isEqualTo(studentEntity.getLastName());
        assertThat(student.getEmail()).isEqualTo(studentEntity.getEmail());
        assertThat(student.getSchoolId()).isEqualTo(studentEntity.getSchoolId());
    }

    @Test
    void testToDTOList() {
        // Arrange
        List<StudentEntity> studentEntities = Arrays.asList(
                StudentEntity.builder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .schoolId(1)
                        .build(),
                StudentEntity.builder()
                        .id(2)
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("jane.doe@example.com")
                        .schoolId(1)
                        .build()
        );

        // Act
        List<Student> students = studentConverter.toDTO(studentEntities);

        // Assert
        assertThat(students).hasSize(2);
        assertThat(students.get(0).getId()).isEqualTo(studentEntities.get(0).getId());
        assertThat(students.get(0).getFirstName()).isEqualTo(studentEntities.get(0).getFirstName());
        // ... and so on for the other fields and the second student
    }

    @Test
    void testFromDTO() {
        // Arrange
        Student student = Student.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();

        // Act
        StudentEntity studentEntity = studentConverter.fromDTO(student);

        // Assert
        assertThat(studentEntity.getId()).isEqualTo(student.getId());
        assertThat(studentEntity.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentEntity.getLastName()).isEqualTo(student.getLastName());
        assertThat(studentEntity.getEmail()).isEqualTo(student.getEmail());
        assertThat(studentEntity.getSchoolId()).isEqualTo(student.getSchoolId());
    }

    @Test
    void testFromDTOList() {
        // Arrange
        List<Student> students = Arrays.asList(
                Student.builder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .schoolId(1)
                        .build(),
                Student.builder()
                        .id(2)
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("jane.doe@example.com")
                        .schoolId(1)
                        .build()
        );

        // Act
        List<StudentEntity> studentEntities = studentConverter.fromDTO(students);

        // Assert
        assertThat(studentEntities).hasSize(2);
        assertThat(studentEntities.get(0).getId()).isEqualTo(students.get(0).getId());
        assertThat(studentEntities.get(0).getFirstName()).isEqualTo(students.get(0).getFirstName());
        // ... and so on for the other fields and the second student
    }
}