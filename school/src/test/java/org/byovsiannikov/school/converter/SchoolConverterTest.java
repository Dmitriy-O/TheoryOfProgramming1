package org.byovsiannikov.school.converter;

import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


class SchoolConverterTest {

    SchoolConverter schoolConverter = Mappers.getMapper(SchoolConverter.class);
    @Test
    void testToDTO() {
        // Arrange
        SchoolEntity schoolEntity = SchoolEntity.builder()
                .id(1)
                .name("Test School")
                .email("test@school.com")
                .build();

        // Act
        School school = schoolConverter.toDTO(schoolEntity);

        // Assert
        assertThat(school.getId()).isEqualTo(schoolEntity.getId());
        assertThat(school.getName()).isEqualTo(schoolEntity.getName());
        assertThat(school.getEmail()).isEqualTo(schoolEntity.getEmail());
    }

    @Test
    void testFromDTO() {
        // Arrange
        School school = School.builder()
                .id(1)
                .name("Test School")
                .email("test@school.com")
                .build();

        // Act
        SchoolEntity schoolEntity = schoolConverter.fromDTO(school);

        // Assert
        assertThat(schoolEntity.getId()).isEqualTo(school.getId());
        assertThat(schoolEntity.getName()).isEqualTo(school.getName());
        assertThat(schoolEntity.getEmail()).isEqualTo(school.getEmail());
    }
}