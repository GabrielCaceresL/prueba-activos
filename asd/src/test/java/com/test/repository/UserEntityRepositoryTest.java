package com.test.repository;

import com.test.entity.CityEntity;
import com.test.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEntityRepositoryTest {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindAllByNameIn() {
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");

        entityManager.persist(userEntity);
        entityManager.flush();

        assertEquals(userEntity, userEntityRepository.findByNumDocument("1234Doc").get());
    }

    @Test
    void testExistsByNumDocument() {
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");

        entityManager.persist(userEntity);
        entityManager.flush();

        assertTrue(userEntityRepository.existsByNumDocument("1234Doc"));
        assertFalse(userEntityRepository.existsByNumDocument("1234"));
    }

}