package org.byovsiannikov.school.services;

import lombok.RequiredArgsConstructor;
import org.byovsiannikov.configserver.logger.behavoral.LoggerDefault;
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

    @Override
    public void saveSchool(School school) {
        SchoolEntity schoolEntity = schoolConverter.fromDTO(school);
        schoolRepository.save(schoolEntity);
        LoggerSingleton.getInstance().setLoggerStrategy(new LoggerSchool(new LoggerDefault())).log("saved");
    }

    @Override
    public List<SchoolEntity> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public FullSchoolResponse findSchoolWithStudents(int schoolId) {
        SchoolEntity school = schoolRepository.findById(schoolId)
                .orElse(SchoolEntity.builder().name("Not found").email("Not found").build());
        var students = studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
