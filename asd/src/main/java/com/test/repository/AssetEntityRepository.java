package com.test.repository;

import com.test.entity.AssetEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssetEntityRepository extends CrudRepository<AssetEntity, Long> {

    @EntityGraph(attributePaths = {"userEntity", "departmentEntity"})
    List<AssetEntity> findByTypeOrPurchaseDateOrSerial(String type, LocalDate purchaseDate, String serial);

    boolean existsByTypeOrSerialOrPurchaseDate(String type, String serial, LocalDate purchaseDate);

    @EntityGraph(attributePaths = {"userEntity", "departmentEntity"})
    Optional<AssetEntity> findBySerial(String serial);

    boolean existsBySerial(String serial);
}
