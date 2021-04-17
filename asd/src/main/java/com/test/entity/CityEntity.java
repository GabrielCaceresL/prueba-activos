package com.test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class CityEntity {

    @Id
    private long idCity;
    private String name;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cityEntities")
    private Set<DepartmentEntity> departmentEntity;

}
