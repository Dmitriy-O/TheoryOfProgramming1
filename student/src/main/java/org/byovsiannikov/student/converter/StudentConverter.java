package org.byovsiannikov.student.converter;

import org.byovsiannikov.student.entity.StudentEntity;
import org.byovsiannikov.student.dto.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentConverter {

    Student toDTO (StudentEntity studentEntity);
    List<Student> toDTO (List<StudentEntity> studentEntity);

    StudentEntity fromDTO (Student student);
    List<StudentEntity> fromDTO (List<Student> student);
}
