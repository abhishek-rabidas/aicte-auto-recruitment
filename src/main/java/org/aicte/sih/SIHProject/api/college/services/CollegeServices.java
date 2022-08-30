package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.admin.Services.AdminServices;
import org.aicte.sih.SIHProject.api.admin.dto.CollegeVacancyRepository;
import org.aicte.sih.SIHProject.api.admin.dto.CollegeVacancyTable;
import org.aicte.sih.SIHProject.api.college.Exceptions.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.dao.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeLoginRequest;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.college.dto.response.ImmediateJoiningResponse;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Request.FacultyLeavingRequest;
import org.aicte.sih.SIHProject.emailing.EmailServices;
import org.aicte.sih.SIHProject.utils.DateFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollegeServices {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private CollegeVacancyRepository collegeVacancyRepository;

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
        college.setPassword(collegeDetails.getPassword()); //TODO encryption
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

    public Long loginCollege(CollegeLoginRequest collegeLoginRequest) {
        College college = collegeRepository.findOneByUin(collegeLoginRequest.getCollegeUin());
        if (college.getPassword().equals(collegeLoginRequest.getPassword()))
            return college.getId();
        else throw new RuntimeException("Login Failed");
    }

    /*public ImmediateJoiningResponse getImmediateJoiningFaculties(FacultyLeavingRequest request) {
        HashSet<Faculty> immediateFacultyList = new HashSet<>();
        HashSet<Faculty> permanentFacultyList = new HashSet<>();

        Faculty leavingFaculty = facultyRepository.findOneById(request.getFacultyId());
        String[] subjects = leavingFaculty.getSubjects().split(",");
        for (String subject : subjects) {
            immediateFacultyList.addAll(facultyRepository.findAllBySubjectsLike(subject, leavingFaculty.getId(), true));
            permanentFacultyList.addAll(facultyRepository.findAllBySubjectsLike(subject, leavingFaculty.getId(), false));
        }

        ImmediateJoiningResponse response = new ImmediateJoiningResponse();
        response.setImmediateFacultyList(new ArrayList<>(immediateFacultyList));
        response.setPermanentFacultyList(new ArrayList<>(permanentFacultyList));

        return response;
    }

    public List<Faculty> getPermanentJoiningFaculties(FacultyLeavingRequest request) {
        HashSet<Faculty> permanentFacultyList = new HashSet<>();

        Faculty leavingFaculty = facultyRepository.findOneById(request.getFacultyId());
        String[] subjects = leavingFaculty.getSubjects().split(",");

        for (String subject : subjects) {
            permanentFacultyList.addAll(facultyRepository.findAllBySubjectsLike(subject, leavingFaculty.getId(), false));
        }
        return new ArrayList<>(permanentFacultyList);
    }*/

    public void postVacancyUpdate(FacultyLeavingRequest facultyLeavingRequest) {
        CollegeVacancyTable collegeVacancyTable = new CollegeVacancyTable();
        collegeVacancyTable.setCollege(collegeRepository.findOneById(facultyLeavingRequest.getCollegeId()));
        Date leavingDate = DateFormatter.parseDateString(facultyLeavingRequest.getDateOfLeaving(), "dd/MM/YYYY");
        Faculty faculty = facultyRepository.findOneById(facultyLeavingRequest.getFacultyId());
        faculty.setDateOfLeaving(leavingDate);
        collegeVacancyTable.setFaculty(facultyRepository.save(faculty));
        collegeVacancyTable.setLeavingDate(leavingDate);
        collegeVacancyRepository.save(collegeVacancyTable);
    }

    public Faculty updateHiredFaculty(Long facultyId, Long collegeId) {
        Faculty hiredFaculty = facultyRepository.findOneById(facultyId);
        hiredFaculty.setAvailable(false);
        hiredFaculty.setAssociatedCollege(collegeRepository.findOneById(collegeId));
        emailServices.sendShortlistedEmail(hiredFaculty, collegeRepository.findOneById(collegeId));
        return facultyRepository.save(hiredFaculty);
    }
}
