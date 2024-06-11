package org.byovsiannikov.school.service;

import org.byovsiannikov.school.FullSchoolResponse;
import org.byovsiannikov.school.converter.SchoolConverter;
import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.dto.Student;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.byovsiannikov.school.repository.SchoolRepository;
import org.byovsiannikov.school.services.SchoolServiceImpl;
import org.byovsiannikov.school.student.StudentClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
public class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private StudentClient studentClient;

    @Mock
    private SchoolConverter schoolConverter;

    @InjectMocks
    private SchoolServiceImpl schoolService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveSchool() {
        School school = new School();
        SchoolEntity schoolEntity = new SchoolEntity();

        when(schoolConverter.fromDTO(school)).thenReturn(schoolEntity);

        schoolService.saveSchool(school);

        verify(schoolRepository, times(1)).save(schoolEntity);
        verify(schoolConverter, times(1)).fromDTO(school);
    }

    @Test
    public void testGetAllSchools() {
        List<SchoolEntity> schoolEntities = Collections.singletonList(new SchoolEntity());

        when(schoolRepository.findAll()).thenReturn(schoolEntities);

        List<SchoolEntity> result = schoolService.getAllSchools();

        assertEquals(schoolEntities, result);
        verify(schoolRepository, times(1)).findAll();
    }

    @Test
    public void testFindSchoolWithStudents() {

        Student student = new Student();
        student.setFirstName("User");
        student.setLastName("LastName");
        student.setEmail("LastName@email.com");
        int schoolId = 1;
        SchoolEntity schoolEntity = SchoolEntity.builder().name("Test School").email("test@school.com").build();
        List<Student> students = List.of(student);

        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(schoolEntity));
        when(studentClient.findAllStudentsBySchool(schoolId)).thenReturn(students);

        FullSchoolResponse response = schoolService.findSchoolWithStudents(schoolId);

        assertEquals("Test School", response.getName());
        assertEquals("test@school.com", response.getEmail());
        assertEquals(students, response.getStudents());
        verify(schoolRepository, times(1)).findById(schoolId);
        verify(studentClient, times(1)).findAllStudentsBySchool(schoolId);
    }

    @Test
    public void testFindSchoolWithStudentsNotFound() {
        int schoolId = 1;
        Student student = new Student();
        student.setFirstName("User");
        student.setLastName("LastName");
        student.setEmail("LastName@email.com");
        List<Student> students = List.of(student);

        when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());
        when(studentClient.findAllStudentsBySchool(schoolId)).thenReturn(students);

        FullSchoolResponse response = schoolService.findSchoolWithStudents(schoolId);

        assertEquals("Not found", response.getName());
        assertEquals("Not found", response.getEmail());
        assertEquals(students, response.getStudents());
        verify(schoolRepository, times(1)).findById(schoolId);
        verify(studentClient, times(1)).findAllStudentsBySchool(schoolId);
    }
}
