package org.aicte.sih.SIHProject.api.admin.Services;

import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Response.FutureReadyFaculty;
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

    public List<Faculty> getRetiringFaculties() {
        return facultyRepository.getFutureRetiredFaculties();
    }

    public List<FutureReadyFaculty> getShortlistedFaculties() {
        List<Faculty> retiringFaculties = facultyRepository.getFutureRetiredFaculties();

        List<FutureReadyFaculty> response = new ArrayList<>();

        for (Faculty faculty : retiringFaculties) {
            List<Faculty> shortlistedFaculties = new ArrayList<>();
            String[] subjects = faculty.getSubjects().split(",");
            for (String subject:subjects) {
                shortlistedFaculties.addAll(facultyRepository.findAllBySubjectsLike(subject));
            }
            FutureReadyFaculty futureReadyFaculty = new FutureReadyFaculty();
            futureReadyFaculty.setCollege(faculty.getAssociatedCollege());
            futureReadyFaculty.setRecommendedFaculties(shortlistedFaculties);
            response.add(futureReadyFaculty);
        }
        return response;
    }
}
