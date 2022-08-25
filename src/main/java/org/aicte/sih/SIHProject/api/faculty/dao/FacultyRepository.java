package org.aicte.sih.SIHProject.api.faculty.dao;

import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findOneById(Long id);

    Long countByEmailAddress(String email);
}
