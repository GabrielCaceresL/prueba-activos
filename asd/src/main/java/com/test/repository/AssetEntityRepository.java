package com.test.repository;

import com.test.entity.AssetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetEntityRepository extends CrudRepository<AssetEntity, Long> {
}
