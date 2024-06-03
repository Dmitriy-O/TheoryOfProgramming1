package org.byovsiannikov.school.repository;

import org.byovsiannikov.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {

}
