package org.aicte.sih.SIHProject.api.college.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;

@Getter
@Setter
public class CollegeResponse {
    private String name;
    private String uin;
    private String aicteAffiliationNumber;
    private String universityRegistrationNumber;
    private String city;
    private String state;
    private String dateOfEstablishment;
    private String email;
    private String phone;

    public CollegeResponse(College college) {
        this.name = college.getName();
        this.uin = college.getUin();
        this.aicteAffiliationNumber = college.getAicteAffiliationNumber();
        this.universityRegistrationNumber = college.getUniversityRegistrationNumber();
        this.city = college.getCity();
        this.state = college.getState();
        this.dateOfEstablishment = college.getDateOfEstablishment();
        this.email = college.getEmail();
        this.phone = college.getPhone();
    }
}
