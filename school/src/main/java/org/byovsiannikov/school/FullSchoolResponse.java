package org.byovsiannikov.school;

import lombok.*;
import org.byovsiannikov.school.dto.Student;

import java.util.List;
@Getter
@AllArgsConstructor
@Builder
public class FullSchoolResponse {
    private String name;
    private String email;
    private List<Student> students;
}
