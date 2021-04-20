package com.test.repository;

import com.test.entity.CityEntity;
import com.test.entity.DepartmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentEntityRepositoryTest {

    @Autowired
    DepartmentEntityRepository departmentEntityRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void testFindByName() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");

        entityManager.persist(cityEntity);
        entityManager.flush();

        Set<CityEntity> cities = new HashSet<>();
        cities.add(cityEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setCityEntities(cities);
        departmentEntity.setName("Department 1");
        entityManager.persist(departmentEntity);
        entityManager.flush();

        assertEquals(departmentEntity, departmentEntityRepository.findByName("Department 1"));
        assertNull(departmentEntityRepository.findByName("Department 2"));

    }

    @Test
    void testExistsByName() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");

        entityManager.persist(cityEntity);
        entityManager.flush();

        Set<CityEntity> cities = new HashSet<>();
        cities.add(cityEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setCityEntities(cities);
        departmentEntity.setName("Department 1");
        entityManager.persist(departmentEntity);
        entityManager.flush();

        assertTrue(departmentEntityRepository.existsByName("Department 1"));
        assertFalse(departmentEntityRepository.existsByName("Department 2"));
    }

}