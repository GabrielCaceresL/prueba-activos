package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<AssetEntity> assetEntity = new HashSet<>();

    public void addToSet(AssetEntity assetEntity) {
        this.assetEntity.add(assetEntity);
        assetEntity.setUserEntity(this);
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDate.now();
    }

    @PrePersist
    void prePersist() {
        this.creationDate = LocalDate.now();
    }
}
