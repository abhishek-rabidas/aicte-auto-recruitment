package org.aicte.sih.SIHProject.api.faculty.services;

import org.aicte.sih.SIHProject.api.college.dao.CollegeRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.commons.FacultyType;
import org.aicte.sih.SIHProject.api.faculty.exception.FacultyException;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.emailing.EmailServices;
import org.aicte.sih.SIHProject.utils.DateFormatter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacultyServices {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private EmailServices emailServices;

    public Faculty registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest) {
        if (facultyRepository.countByEmailAddress(facultyRegistrationRequest.getEmailAddress()) > 0) {
            throw new FacultyException("Faculty Exists with this email address");
        }

        Faculty faculty = new Faculty();
        faculty.setFirstName(facultyRegistrationRequest.getFirstName());
        faculty.setLastName(facultyRegistrationRequest.getLastName());
        faculty.setStreet(facultyRegistrationRequest.getStreet());
        faculty.setCity(facultyRegistrationRequest.getCity());
        faculty.setState(facultyRegistrationRequest.getState());
        faculty.setPinCode(facultyRegistrationRequest.getPinCode());
        faculty.setAadharNumber(facultyRegistrationRequest.getAadharNumber());
        faculty.setPhoneNumber(facultyRegistrationRequest.getPhoneNumber());
        faculty.setEmailAddress(facultyRegistrationRequest.getEmailAddress());
        faculty.setDescription(facultyRegistrationRequest.getDescription());
        faculty.setSubjects(facultyRegistrationRequest.getSubjects());
        faculty.setDateOfBirth(DateFormatter.parseDateString(facultyRegistrationRequest.getDateOfBirth(), "dd/MM/yyyy"));
        faculty.setDateOfRetirement(getRetirementDate(faculty.getDateOfBirth()));
        if(facultyRegistrationRequest.getCollegeUin() != null)
            faculty.setAssociatedCollege(collegeRepository.findOneByUin(facultyRegistrationRequest.getCollegeUin()));
        switch (facultyRegistrationRequest.getFacultyType()) {
            case "PROFESSOR" :
                faculty.setFacultyType(FacultyType.PROFESSOR);
                break;
            case "ASSISTANT_PROFESSOR":
                faculty.setFacultyType(FacultyType.ASSISTANT_PROFESSOR);
                break;
            case "ASSOCIATE_PROFESSOR":
                faculty.setFacultyType(FacultyType.ASSOCIATE_PROFESSOR);
                break;
            default:
                faculty.setFacultyType(FacultyType.OTHER);
        }
        faculty.setImmediateJoin(facultyRegistrationRequest.isImmediateJoin());
        faculty.setAvailable(facultyRegistrationRequest.isAvailable());
        faculty.setDateOfLeaving(faculty.getDateOfRetirement());
        try {
            emailServices.sendFacultyRegistrationSuccessfulEmail(faculty);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            return facultyRepository.save(faculty);
        }
    }

    private Date getRetirementDate(Date dob) {
        return DateUtils.addYears(dob, 58);
    }
}
