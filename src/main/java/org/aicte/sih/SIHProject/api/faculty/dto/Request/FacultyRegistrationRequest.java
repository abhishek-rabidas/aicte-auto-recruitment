package org.aicte.sih.SIHProject.api.faculty.dto.Request;

import lombok.Getter;

@Getter
public class FacultyRegistrationRequest {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String aadharNumber;
    private String phoneNumber;
    private String emailAddress;
    private String description;
    private String dateOfBirth;
    private String subjects;
    private Long collegeId;
    private boolean immediateJoin;
    private boolean available;
}
