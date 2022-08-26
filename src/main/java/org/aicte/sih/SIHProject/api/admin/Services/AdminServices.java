package org.aicte.sih.SIHProject.api.admin.Services;

import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Response.FutureReadyFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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

    public List<Faculty> getRetiringFaculties(Long id) {
        return facultyRepository.getFutureRetiredFaculties(id);
    }

/*    public List<FutureReadyFaculty> getShortlistedFaculties() {
        List<Faculty> retiringFaculties = facultyRepository.getFutureRetiredFaculties();

        List<FutureReadyFaculty> response = new ArrayList<>();

        for (Faculty faculty : retiringFaculties) {
            HashSet<Faculty> shortlistedFaculties = new HashSet<>();
            String[] subjects = faculty.getSubjects().split(",");

            for (String subject:subjects) {
                shortlistedFaculties.addAll(facultyRepository.findAllBySubjectsLike(subject, faculty.getId(), false));
            }

            FutureReadyFaculty futureReadyFaculty = new FutureReadyFaculty();
            futureReadyFaculty.setCollege(faculty.getAssociatedCollege());

            if (!shortlistedFaculties.isEmpty())
                futureReadyFaculty.setRecommendedFaculties(new ArrayList<>(shortlistedFaculties));

            if(!(futureReadyFaculty.getRecommendedFaculties() == null || futureReadyFaculty.getCollege() == null))
                response.add(futureReadyFaculty);
        }
        return response;
    }*/
}
