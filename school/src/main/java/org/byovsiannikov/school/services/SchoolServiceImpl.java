package org.byovsiannikov.school.services;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.configserver.logger.behavoral.LoggerSchool;
import org.byovsiannikov.configserver.logger.creational.LoggerSingleton;
import org.byovsiannikov.school.FullSchoolResponse;
import org.byovsiannikov.school.converter.SchoolConverter;
import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.byovsiannikov.school.repository.SchoolRepository;
import org.byovsiannikov.school.student.StudentClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;
    private final SchoolConverter schoolConverter;

    public void saveSchool (School school) {
        schoolRepository.save(schoolConverter.fromDTO(school));
        LoggerSingleton.getInstance().setLoggerStrategy(new LoggerSchool()).log("saved");
    }

    public List<SchoolEntity> getAllSchools () {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolWithStudents (int schoolId) {
        var schools = schoolRepository.findById(schoolId).orElse(SchoolEntity.builder().name("Not found").email("Not found").build());
        var students = studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder().name(schools.getName()).email(schools.getEmail()).students(students).build();
    }

}
