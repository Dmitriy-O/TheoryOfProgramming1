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
        StudentEntity studentEntity = StudentEntity.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();

        Student student = studentConverter.toDTO(studentEntity);

        assertThat(student.getId()).isEqualTo(studentEntity.getId());
        assertThat(student.getFirstName()).isEqualTo(studentEntity.getFirstName());
        assertThat(student.getLastName()).isEqualTo(studentEntity.getLastName());
        assertThat(student.getEmail()).isEqualTo(studentEntity.getEmail());
        assertThat(student.getSchoolId()).isEqualTo(studentEntity.getSchoolId());
    }

    @Test
    void testToDTOList() {
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

        List<Student> students = studentConverter.toDTO(studentEntities);

        assertThat(students).hasSize(2);
        assertThat(students.get(0).getId()).isEqualTo(studentEntities.get(0).getId());
        assertThat(students.get(0).getFirstName()).isEqualTo(studentEntities.get(0).getFirstName());
    }

    @Test
    void testFromDTO() {
        Student student = Student.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();

        StudentEntity studentEntity = studentConverter.fromDTO(student);

        assertThat(studentEntity.getId()).isEqualTo(student.getId());
        assertThat(studentEntity.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentEntity.getLastName()).isEqualTo(student.getLastName());
        assertThat(studentEntity.getEmail()).isEqualTo(student.getEmail());
        assertThat(studentEntity.getSchoolId()).isEqualTo(student.getSchoolId());
    }

    @Test
    void testFromDTOList() {
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

        List<StudentEntity> studentEntities = studentConverter.fromDTO(students);

        assertThat(studentEntities).hasSize(2);
        assertThat(studentEntities.get(0).getId()).isEqualTo(students.get(0).getId());
        assertThat(studentEntities.get(0).getFirstName()).isEqualTo(students.get(0).getFirstName());
    }
}