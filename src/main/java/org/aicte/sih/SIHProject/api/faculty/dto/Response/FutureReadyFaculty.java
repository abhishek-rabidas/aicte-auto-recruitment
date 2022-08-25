package org.aicte.sih.SIHProject.api.faculty.dto.Response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;

import java.util.List;

@Setter
@Getter
public class FutureReadyFaculty {
    private College college;
    private List<Faculty> recommendedFaculties;
}
