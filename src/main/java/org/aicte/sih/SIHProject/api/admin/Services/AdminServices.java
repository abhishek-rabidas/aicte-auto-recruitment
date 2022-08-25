package org.aicte.sih.SIHProject.api.admin.Services;

import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.stereotype.Service;

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

    protected List<Faculty> getRetiringFaculties() {
        return null;
    }

    protected List<Faculty> getShortlistedFaculties() {
        return null;
    }
}
