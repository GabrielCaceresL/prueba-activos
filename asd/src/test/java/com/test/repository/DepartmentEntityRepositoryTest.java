package com.test.repository;

import com.test.entity.CityEntity;
import com.test.entity.DepartmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Disabled
class DepartmentEntityRepositoryTest {

    @Autowired
    DepartmentEntityRepository departmentEntityRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Bogota");


        testEntityManager.persistAndFlush(cityEntity);

        //List<DepartmentEntity> departmentEntityList = new ArrayList<>();
        IntStream.range(0,10).forEach(i -> {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.addCity(cityEntity);
            departmentEntity.setName("Departamento test "+ i);
            //departmentEntityList.add(departmentEntity);
            testEntityManager.persistAndFlush(departmentEntity);
        });

      //  departmentEntityRepository.saveAll(departmentEntityList);
    }

    @Test
    @Disabled
    void testGetDepartments(){
        List<DepartmentEntity> departmentEntityList = (List<DepartmentEntity>) departmentEntityRepository.findAll();
        assertFalse(departmentEntityList.isEmpty(), "La lista está vacia");
        assertEquals("Departamento test 8",departmentEntityList.stream()
                .map(DepartmentEntity :: getName)
                .filter(args -> "Departamento test 8".equals(args))
                .findAny().get(), "No existe el elemento");

    }


}