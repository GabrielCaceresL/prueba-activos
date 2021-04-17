package com.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCity;
    private String name;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cityEntities")
    private Set<DepartmentEntity> departmentEntity;

}
