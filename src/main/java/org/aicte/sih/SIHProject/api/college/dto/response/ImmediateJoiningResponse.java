package org.aicte.sih.SIHProject.api.college.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;

import java.util.List;

@Setter
@Getter
public class ImmediateJoiningResponse {
    private List<Faculty> immediateFacultyList;
    private List<Faculty> permanentFacultyList;
}
