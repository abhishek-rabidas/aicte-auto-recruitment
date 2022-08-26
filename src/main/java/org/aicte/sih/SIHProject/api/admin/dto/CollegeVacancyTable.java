package org.aicte.sih.SIHProject.api.admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class CollegeVacancyTable extends AbstractPersistable<Long> {
    @OneToOne
    private College college;
    @OneToOne
    private Faculty faculty;
    private Date leavingDate;
}
