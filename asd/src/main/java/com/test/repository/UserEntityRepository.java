package com.test.repository;

import com.test.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = {"assetEntity"})
    Optional<UserEntity> findByNumDocument(String numDocument);


}
