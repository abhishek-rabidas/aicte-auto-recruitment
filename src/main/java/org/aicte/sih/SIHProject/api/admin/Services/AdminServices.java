package org.aicte.sih.SIHProject.api.admin.Services;

import org.aicte.sih.SIHProject.api.admin.dto.CollegeVacancyRepository;
import org.aicte.sih.SIHProject.api.college.dao.CollegeRepository;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AICTE Services</h1>
 * <br />
 * <p>Fetch the faculties retiring after 3 months</p>
 * <p>Find their replacement before 3 months</p>
 * <p>Send these faculties data to college</p>
 */

@Service
public class AdminServices {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeVacancyRepository collegeVacancyRepository;

    public List<Faculty> getRetiringFaculties(Long id) {
        List<Faculty> result = new ArrayList<>();
        result.addAll(facultyRepository.getFutureRetiredFaculties(id)); //retiring faculties
        result.addAll(collegeVacancyRepository.findAllFacultyByCollege(collegeRepository.findOneById(id))); //leaving faculties
        return result;
    }

}
