package com.test.repository;

import com.test.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEntityRepository extends CrudRepository<DepartmentEntity, Long> {
}
