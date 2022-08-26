package org.aicte.sih.SIHProject.api.college.controller;

import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeLoginRequest;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.college.dto.response.ImmediateJoiningResponse;
import org.aicte.sih.SIHProject.api.college.services.CollegeServices;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Request.FacultyLeavingRequest;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v2/college")
@CrossOrigin()
public class CollegeController {

    @Autowired
    private CollegeServices collegeServices;

    @PostMapping()
    public ResponseEntity<APIResponse<College>> registerCollege(@RequestBody CollegeRegistrationRequest request) {
        APIResponse<College> response = new APIResponse<>();
        try {
            response.setData(collegeServices.registerCollege(request));
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<Long>> loginCollege(@RequestBody CollegeLoginRequest collegeLoginRequest) {
        APIResponse<Long> response = new APIResponse<>();
        try {
            response.setData(collegeServices.loginCollege(collegeLoginRequest));
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * Immediate Joining Request
     */

    @PostMapping("/getImmediateJoiningFaculties")
    public ResponseEntity<APIResponse<ImmediateJoiningResponse>> immediateJoiningFaculty(@RequestBody FacultyLeavingRequest request) {
        APIResponse<ImmediateJoiningResponse> response = new APIResponse<>();
        try {
            response.setData(collegeServices.getImmediateJoiningFaculties(request));
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/getPermanentJoiningFaculties")
    public ResponseEntity<APIResponse<List<Faculty>>> permanentJoiningList(@RequestBody FacultyLeavingRequest request) {
        APIResponse<List<Faculty>> response = new APIResponse<>();
        try {
            response.setData(collegeServices.getPermanentJoiningFaculties(request));
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/postVacancy")
    public ResponseEntity<APIResponse> postVacancyUpdate(@RequestBody FacultyLeavingRequest facultyLeavingRequest) {
        APIResponse response = new APIResponse<>();
        try {
            collegeServices.postVacancyUpdate(facultyLeavingRequest);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
