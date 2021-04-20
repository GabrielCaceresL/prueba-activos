package com.test.repository;

import com.test.entity.CityEntity;
import com.test.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CityEntityRepository extends CrudRepository<CityEntity, Long> {

    Set<CityEntity> findAllByNameIn(Collection<String> cityNames);

    Optional<CityEntity> findByCodeCity(String CodeCity);

    boolean existsByName(String cityName);

    boolean existsByCodeCity(String id);

}
