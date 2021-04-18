package com.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cityEntities")
    private Set<DepartmentEntity> departmentEntity = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDate.now();
    }

    @PrePersist
    void prePersist() {
        this.creationDate = LocalDate.now();
    }

}
