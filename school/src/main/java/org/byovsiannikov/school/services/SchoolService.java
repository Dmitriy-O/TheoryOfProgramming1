package org.byovsiannikov.school.services;

import org.byovsiannikov.school.FullSchoolResponse;
import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.entity.SchoolEntity;

import java.util.List;

public interface SchoolService {

    void saveSchool (School school);

    List<SchoolEntity> getAllSchools ();

    FullSchoolResponse findSchoolWithStudents (int schoolId);
}
