package org.byovsiannikov.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.byovsiannikov.school.FullSchoolResponse;
import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.dto.Student;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.byovsiannikov.school.services.SchoolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
class SchoolControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolServiceImpl schoolService;

    @Autowired
    private ObjectMapper objectMapper;

    private School school;
    private SchoolEntity schoolEntity;
    private FullSchoolResponse fullSchoolResponse;

    @BeforeEach
    public void setUp() {
        school = new School();
        school.setName("Test School");
        school.setEmail("test@school.com");

        schoolEntity = SchoolEntity.builder()
                .name("Test School")
                .email("test@school.com")
                .build();

        fullSchoolResponse = FullSchoolResponse.builder()
                .name("Test School")
                .email("test@school.com")
                .students(Collections.singletonList(Student.builder().firstName("firstName").lastName("lastName").email("testmail@com").build()))
                .build();
    }

    @Test
    public void createSchool_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(post("/api/v1/schools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(school)))
                .andExpect(status().isCreated());

        Mockito.verify(schoolService, Mockito.times(1)).saveSchool(Mockito.any(School.class));
    }

    @Test
    public void getAllSchools_ShouldReturnAllSchools() throws Exception {
        List<SchoolEntity> schoolEntities = Collections.singletonList(schoolEntity);

        Mockito.when(schoolService.getAllSchools()).thenReturn(schoolEntities);

        mockMvc.perform(get("/api/v1/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Test School")))
                .andExpect(jsonPath("$[0].email", is("test@school.com")));

        Mockito.verify(schoolService, Mockito.times(1)).getAllSchools();
    }

    @Test
    public void getAllSchoolsWithStudents_ShouldReturnFullSchoolResponse() throws Exception {
        // Arrange
        List<Student> students = Collections.singletonList(
                Student.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .email("testmail@com")
                        .build()
        );

        fullSchoolResponse = FullSchoolResponse.builder()
                .name("Test School")
                .email("test@school.com")
                .students(students)
                .build();

        Mockito.when(schoolService.findSchoolWithStudents(anyInt())).thenReturn(fullSchoolResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/schools/with-students/{school-id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test School")))
                .andExpect(jsonPath("$.email", is("test@school.com")))
                .andExpect(jsonPath("$.students[0].firstName", is("firstName")))
                .andExpect(jsonPath("$.students[0].lastName", is("lastName")))
                .andExpect(jsonPath("$.students[0].email", is("testmail@com")));

        Mockito.verify(schoolService, Mockito.times(1)).findSchoolWithStudents(anyInt());
    }


}