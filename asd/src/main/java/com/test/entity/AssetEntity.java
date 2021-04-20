package com.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String type;
    private String serial;
    private String numInventory;
    private String weightKg;
    private String highCm;
    private String widthCm;
    private String longAssetCm;
    private Double purchaseValue;

    private LocalDate purchaseDate;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private DepartmentEntity departmentEntity;

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDate.now();
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now();
    }

}
