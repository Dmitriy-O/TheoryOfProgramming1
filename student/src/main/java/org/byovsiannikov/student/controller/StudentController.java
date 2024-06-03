package org.byovsiannikov.student.controller;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.student.Student;
import org.byovsiannikov.student.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
       return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> getAllStudents(
            @PathVariable("school-id") int schoolId
    ) {
        return ResponseEntity.ok(studentService.findAllStudentsBySchool(schoolId));
    }
}
