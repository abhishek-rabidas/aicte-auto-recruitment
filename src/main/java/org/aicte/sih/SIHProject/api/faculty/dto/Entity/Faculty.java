package org.aicte.sih.SIHProject.api.faculty.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.faculty.dto.commons.FacultyType;
import org.aicte.sih.SIHProject.api.faculty.dto.commons.Stream;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Faculty extends AbstractPersistable<Long> {
    private String firstName;
    private String lastName;
    private String street;
    @Column(unique = true)
    private String aadharNumber;
    private String city;
    private String state;
    private int pinCode;
    private String phoneNumber;
    @Column(unique = true)
    private String emailAddress;
    private String description;
    private Date dateOfBirth;
    private Date dateOfRetirement;
    private Date dateOfLeaving;
    @Enumerated(EnumType.STRING)
    private Stream stream;
    private String subjects;
    @OneToOne
    private College associatedCollege;
    private boolean immediateJoin;
    private boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private FacultyType facultyType;
}
