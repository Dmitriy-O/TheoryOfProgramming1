package org.byovsiannikov.student.repository;

import org.byovsiannikov.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllBySchoolId (int schoolId);
}