package org.aicte.sih.SIHProject.api.admin.dto;

import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeVacancyRepository extends JpaRepository<CollegeVacancyTable, Long> {
    List<CollegeVacancyTable> findAllByCollege(College college);
}
