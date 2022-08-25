package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Exceptions.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.dao.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.emailing.EmailServices;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CollegeServices {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private EmailServices emailServices;

    public College registerCollege(CollegeRegistrationRequest collegeDetails) {
        if (collegeRepository.countByAicteAffiliationNumber(collegeDetails.getAicteAffiliationNumber()) > 0 ||
                collegeRepository.countByUniversityRegistrationNumber(collegeDetails.getUniversityRegistrationNumber()) > 0 ||
                collegeRepository.countByEmail(collegeDetails.getEmail()) > 0 || collegeRepository.countByPhone(collegeDetails.getPhone()) > 0) {
            throw new CollegeExceptions("College is already registered");
        }

        College college = new College();
        college.setName(collegeDetails.getName());
        college.setUin(collegeDetails.getName().replaceAll(" ", "").toLowerCase(Locale.ROOT) + "_" + RandomStringUtils.randomAlphanumeric(8));
        college.setAicteAffiliationNumber(collegeDetails.getAicteAffiliationNumber());
        college.setUniversityRegistrationNumber(collegeDetails.getUniversityRegistrationNumber());
        college.setCity(collegeDetails.getCity());
        college.setState(collegeDetails.getState());
        college.setDateOfEstablishment(collegeDetails.getDateOfEstablishment());
        college.setPhone(collegeDetails.getPhone());
        college.setEmail(collegeDetails.getEmail());
        college.setCoverImageBaseUrl(collegeDetails.getCoverImageBaseUrl());
        college.setActive(true);
        try {
            emailServices.sendCollegeRegistrationSuccessfulEmail(college);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            return collegeRepository.save(college);
        }
    }
}
