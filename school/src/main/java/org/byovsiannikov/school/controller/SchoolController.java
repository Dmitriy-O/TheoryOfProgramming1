package org.byovsiannikov.school.controller;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.school.FullSchoolResponse;
import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.byovsiannikov.school.services.SchoolServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolServiceImpl schoolService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSchool (@RequestBody School school) {
        schoolService.saveSchool(school);
    }
    @GetMapping
    public ResponseEntity<List<SchoolEntity>> getAllSchools () {
       return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> getAllSchools (@PathVariable("school-id") int schoolId) {
        return ResponseEntity.ok(schoolService.findSchoolWithStudents(schoolId));
    }
}
