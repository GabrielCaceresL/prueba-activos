package com.test.repository;

import com.test.entity.CityEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CityEntityRepositoryTest {

    @Autowired
    private CityEntityRepository cityEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindAllByNameIn() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");
        entityManager.persist(cityEntity);
        entityManager.flush();

        Set<CityEntity> cities = new HashSet<>();
        cities.add(cityEntity);

        List<String> colName = new ArrayList<>();
        colName.add(cityEntity.getName());

        assertEquals(cities, cityEntityRepository.findAllByNameIn(colName));
    }

    @Test
    void findByCodeCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");
        entityManager.persist(cityEntity);
        entityManager.flush();

        Set<CityEntity> cities = new HashSet<>();
        cities.add(cityEntity);

        List<String> colName = new ArrayList<>();
        colName.add(cityEntity.getName());

        assertEquals(cityEntity, cityEntityRepository.findByCodeCity("CT1").get());
    }

    @Test
    void existsByName() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");
        entityManager.persist(cityEntity);
        entityManager.flush();

        Set<CityEntity> cities = new HashSet<>();
        cities.add(cityEntity);

        List<String> colName = new ArrayList<>();
        colName.add(cityEntity.getName());

        assertTrue(cityEntityRepository.existsByName("Cucuta"));
        assertFalse(cityEntityRepository.existsByName("Bogota"));
    }

    @Test
    void existsByCodeCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Cucuta");
        cityEntity.setCodeCity("CT1");
        entityManager.persist(cityEntity);
        entityManager.flush();

        assertTrue(cityEntityRepository.existsByCodeCity("CT1"));
        assertFalse(cityEntityRepository.existsByCodeCity("CT2"));
    }

}