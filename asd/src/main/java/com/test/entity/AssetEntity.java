package com.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String type;
    private String serial;
    private String numbInventory;
    private String weight;
    private String high;
    private String width;
    private String longAsset;
    private String purchaseValue;
    private LocalDate purchaseDate;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity userEntity;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id", nullable = false)
    private DepartmentEntity departmentEntity;


}
