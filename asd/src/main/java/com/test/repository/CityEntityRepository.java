package com.test.repository;

import com.test.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface CityEntityRepository extends CrudRepository<CityEntity, Long> {

    Set<CityEntity> findAllByNameIn(Collection<String> cityNames);

}
