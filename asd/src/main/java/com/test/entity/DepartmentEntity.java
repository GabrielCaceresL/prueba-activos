package com.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idArea;
    private String name;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentEntity")
    private Set<AssetEntity> assetEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<CityEntity> cityEntities;
}
