package com.test.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NaturalId
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
