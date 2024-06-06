package org.byovsiannikov.student.repository;

import org.byovsiannikov.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    List<StudentEntity> findAllBySchoolId (int schoolId);
}
