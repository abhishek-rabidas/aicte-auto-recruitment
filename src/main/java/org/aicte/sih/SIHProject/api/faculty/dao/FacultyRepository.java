package org.aicte.sih.SIHProject.api.faculty.dao;

import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findOneById(Long id);

    Long countByEmailAddress(String email);

    @Query(value = "SELECT * FROM faculty WHERE MONTH(date_of_retirement) = (SELECT MONTH(NOW())) + 3 AND faculty.associated_college_id = :id", nativeQuery = true)
    List<Faculty> getFutureRetiredFaculties(@Param("id") Long id);

    @Query(value = "SELECT * FROM faculty WHERE faculty.stream = :stream AND faculty.id != :id AND faculty.is_available = true AND faculty.faculty_type = :facultyType"
            , nativeQuery = true)
    List<Faculty> findAllBySameStream(@Param("stream") String stream, @Param("id") Long id, @Param("facultyType") String facultyType);
}
