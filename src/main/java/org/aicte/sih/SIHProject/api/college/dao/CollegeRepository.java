package org.aicte.sih.SIHProject.api.college.dao;

import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
    College findOneByUin(String uin);
    College findOneById(Long id);
    long countByAicteAffiliationNumber(String data);
    long countByUniversityRegistrationNumber(String data);
    long countByEmail(String data);
    long countByPhone(String data);
}
