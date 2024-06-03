package org.byovsiannikov.school.converter;

import org.byovsiannikov.school.dto.School;
import org.byovsiannikov.school.entity.SchoolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolConverter {
    School toDTO(SchoolEntity schoolEntity);
    SchoolEntity fromDTO(School school);
}
