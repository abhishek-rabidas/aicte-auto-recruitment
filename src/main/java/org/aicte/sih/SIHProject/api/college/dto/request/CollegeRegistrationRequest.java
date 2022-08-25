package org.aicte.sih.SIHProject.api.college.dto.request;

import lombok.Getter;

@Getter
public class CollegeRegistrationRequest {
    private String name;
    private String uin;
    private String password;
    private String aicteAffiliationNumber;
    private String universityRegistrationNumber;
    private String city;
    private String state;
    private String dateOfEstablishment;
    private String email;
    private String phone;
    private String coverImageBaseUrl;
    private boolean isActive;
}
