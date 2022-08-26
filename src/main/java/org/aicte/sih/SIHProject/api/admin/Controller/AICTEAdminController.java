package org.aicte.sih.SIHProject.api.admin.Controller;

import org.aicte.sih.SIHProject.api.admin.Services.AdminServices;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Response.FutureReadyFaculty;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
@CrossOrigin
public class AICTEAdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/getVacancies/{collegeId}")
    public ResponseEntity<APIResponse<List<Faculty>>> getVacancies(@PathVariable("collegeId") Long collegeId) {
        APIResponse<List<Faculty>> response = new APIResponse<>();
        try {
            response.setData(adminServices.getRetiringFaculties(collegeId));
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

    @GetMapping("/shortlistedFaculties/{id}")
    public ResponseEntity<APIResponse<List<Faculty>>> getShortlistedFaculties(@PathVariable("id") Long id) {
        APIResponse<List<Faculty>> response = new APIResponse<>();
        try {
            response.setData(adminServices.getReplacementFaculties(id));
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
