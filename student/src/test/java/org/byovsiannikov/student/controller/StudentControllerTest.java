package org.byovsiannikov.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.byovsiannikov.student.dto.Student;
import org.byovsiannikov.student.services.StudentServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .schoolId(1)
                .build();
    }

    @Test
    public void createStudent_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());

        Mockito.verify(studentService, Mockito.times(1)).saveStudent(any(Student.class));
    }

    @Test
    public void getAllStudents_ShouldReturnAllStudents() throws Exception {
        List<Student> students = Collections.singletonList(student);

        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")))
                .andExpect(jsonPath("$[0].schoolId", is(1)));

        Mockito.verify(studentService, Mockito.times(1)).getAllStudents();
    }

    @Test
    public void getAllStudentsBySchool_ShouldReturnStudents() throws Exception {
        List<Student> students = Collections.singletonList(student);

        Mockito.when(studentService.findAllStudentsBySchool(anyInt())).thenReturn(students);

        mockMvc.perform(get("/api/v1/students/school/{school-id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")))
                .andExpect(jsonPath("$[0].schoolId", is(1)));

        Mockito.verify(studentService, Mockito.times(1)).findAllStudentsBySchool(anyInt());
    }
}
