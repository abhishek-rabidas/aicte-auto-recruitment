package org.aicte.sih.SIHProject.api.faculty.controller;

import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.services.FacultyServices;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/api/v2/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyServices facultyServices;

    @PostMapping
    public ResponseEntity<APIResponse<Faculty>> registerFaculty(@RequestBody FacultyRegistrationRequest request) {
        APIResponse<Faculty> response = new APIResponse<>();
        try {
            response.setData(facultyServices.registerFaculty(request));
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
