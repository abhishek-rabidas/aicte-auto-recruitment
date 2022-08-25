package org.aicte.sih.SIHProject.api.faculty.dao;

import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findOneById(Long id);

    Long countByEmailAddress(String email);

    @Query(value = "SELECT * FROM faculty WHERE MONTH(date_of_retirement) = (SELECT MONTH(NOW())) + 3", nativeQuery = true)
    List<Faculty> getFutureRetiredFaculties();

    @Query(value = "SELECT * FROM faculty WHERE subjects LIKE %:subject% AND faculty.id != :id AND faculty.is_available = true AND faculty.immediate_join = " +
            ":immediateJoin", nativeQuery = true)
    List<Faculty> findAllBySubjectsLike(@Param("subject") String subject, @Param("id") Long id, @Param("immediateJoin") boolean immediateJoin);

    //List<Faculty> findAllBySubjectsLike(String subject);
}
