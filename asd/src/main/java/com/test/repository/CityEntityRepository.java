package com.test.repository;

import com.test.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityEntityRepository extends CrudRepository<CityEntity, Long> {
}
