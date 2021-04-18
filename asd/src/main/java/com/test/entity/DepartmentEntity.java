package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentEntity")
    private Set<AssetEntity> assetEntity = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<CityEntity> cityEntities = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDate.now();
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now();
    }

    public void addCity(CityEntity cityEntity){
        this.cityEntities.add(cityEntity);
    }

    public void removeCity(CityEntity cityEntityRemove){
       Iterator<CityEntity> iterator = this.cityEntities.iterator();
       while(iterator.hasNext()){
           CityEntity cityEntity = iterator.next();
           if(cityEntity.equals(cityEntityRemove)){
               iterator.remove();
               cityEntityRemove.getDepartmentEntity().remove(this);
           }
       }
    }

    public Set<String> nameCities(){
        return this.cityEntities.stream().map(CityEntity::getName).collect(Collectors.toSet());
    }

}
