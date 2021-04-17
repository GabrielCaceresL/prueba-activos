package com.test.entity;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class UserEntity {

    @Id
    private Long id;
    private String numDocument;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private LocalDate birthday;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<AssetEntity>  assetEntity;
}
