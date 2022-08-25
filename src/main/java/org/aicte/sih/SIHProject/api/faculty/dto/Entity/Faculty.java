package org.aicte.sih.SIHProject.api.faculty.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Faculty extends AbstractPersistable<Long> {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String phoneNumber;
    private String emailAddress;
    private String description;
    private Date dateOfBirth;
    private Date dateOfRetirement;

    @OneToOne
    private College associatedCollege;
}
