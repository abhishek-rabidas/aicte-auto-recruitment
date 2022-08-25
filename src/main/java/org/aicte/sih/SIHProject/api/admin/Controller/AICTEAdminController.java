package org.aicte.sih.SIHProject.api.admin.Controller;

import org.aicte.sih.SIHProject.api.admin.Services.AdminServices;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.Response.FutureReadyFaculty;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
public class AICTEAdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/getRetiringFaculties")
    public ResponseEntity<APIResponse<List<Faculty>>> getRetiringFaculties() {
        APIResponse<List<Faculty>> response = new APIResponse<>();
        try {
            response.setData(adminServices.getRetiringFaculties());
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

    @GetMapping("/shortlistedFaculties")
    public ResponseEntity<APIResponse<List<FutureReadyFaculty>>> getShortlistedFaculties() {
        APIResponse<List<FutureReadyFaculty>> response = new APIResponse<>();
        try {
            response.setData(adminServices.getShortlistedFaculties());
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
