package com.test.repository;

import com.test.entity.AssetEntity;
import com.test.entity.CityEntity;
import com.test.entity.DepartmentEntity;
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
class AssetEntityRepositoryTest {

    @Autowired
    private AssetEntityRepository assetEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByTypeOrPurchaseDateOrSerialDepartment(){
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

        AssetEntity assetEntityDepartment = new AssetEntity();
        assetEntityDepartment.setDepartmentEntity(departmentEntity);
        assetEntityDepartment.setLongAssetCm("10");
        assetEntityDepartment.setName("10");
        assetEntityDepartment.setDescription("10");
        assetEntityDepartment.setType("Fijo");
        assetEntityDepartment.setSerial("1234");
        assetEntityDepartment.setNumInventory("1111");
        assetEntityDepartment.setWeightKg("10");
        assetEntityDepartment.setHighCm("10");
        assetEntityDepartment.setWidthCm("10");
        assetEntityDepartment.setLongAssetCm("10");
        assetEntityDepartment.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityDepartment.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityDepartment);
        entityManager.flush();

        List<AssetEntity> assetEntityList = new ArrayList<>();
        assetEntityList.add(assetEntityDepartment);

        assertEquals(assetEntityList, assetEntityRepository
                .findByTypeOrPurchaseDateOrSerial("",LocalDate.now(),"1234"));

    }

    @Test
    void testFindByTypeOrPurchaseDateOrSerialUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");
        entityManager.persist(userEntity);
        entityManager.flush();

        AssetEntity assetEntityUser = new AssetEntity();
        assetEntityUser.setUserEntity(userEntity);
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setName("10");
        assetEntityUser.setDescription("10");
        assetEntityUser.setType("Fijo");
        assetEntityUser.setSerial("4321");
        assetEntityUser.setNumInventory("1111");
        assetEntityUser.setWeightKg("10");
        assetEntityUser.setHighCm("10");
        assetEntityUser.setWidthCm("10");
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityUser.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityUser);
        entityManager.flush();

        List<AssetEntity> assetEntityList = new ArrayList<>();
        assetEntityList.add(assetEntityUser);

        assertEquals(assetEntityList, assetEntityRepository
                .findByTypeOrPurchaseDateOrSerial("",LocalDate.now(),"4321"));

    }


    @Test
    void testExistsByTypeOrSerialOrPurchaseDateDepartment(){
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

        AssetEntity assetEntityDepartment = new AssetEntity();
        assetEntityDepartment.setDepartmentEntity(departmentEntity);
        assetEntityDepartment.setLongAssetCm("10");
        assetEntityDepartment.setName("10");
        assetEntityDepartment.setDescription("10");
        assetEntityDepartment.setType("Fijo");
        assetEntityDepartment.setSerial("1234");
        assetEntityDepartment.setNumInventory("1111");
        assetEntityDepartment.setWeightKg("10");
        assetEntityDepartment.setHighCm("10");
        assetEntityDepartment.setWidthCm("10");
        assetEntityDepartment.setLongAssetCm("10");
        assetEntityDepartment.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityDepartment.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityDepartment);
        entityManager.flush();

        List<AssetEntity> assetEntityList = new ArrayList<>();
        assetEntityList.add(assetEntityDepartment);

        assertTrue(assetEntityRepository
                .existsByTypeOrSerialOrPurchaseDate("","1234", LocalDate.now()));
        assertFalse(assetEntityRepository
                .existsByTypeOrSerialOrPurchaseDate("","3214",LocalDate.of(2021,01,01)));
    }

    @Test
    void testExistsByTypeOrSerialOrPurchaseDateUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");
        entityManager.persist(userEntity);
        entityManager.flush();

        AssetEntity assetEntityUser = new AssetEntity();
        assetEntityUser.setUserEntity(userEntity);
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setName("10");
        assetEntityUser.setDescription("10");
        assetEntityUser.setType("Fijo");
        assetEntityUser.setSerial("4321");
        assetEntityUser.setNumInventory("1111");
        assetEntityUser.setWeightKg("10");
        assetEntityUser.setHighCm("10");
        assetEntityUser.setWidthCm("10");
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityUser.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityUser);
        entityManager.flush();

        List<AssetEntity> assetEntityList = new ArrayList<>();
        assetEntityList.add(assetEntityUser);

        assertTrue(assetEntityRepository
                .existsByTypeOrSerialOrPurchaseDate("Fijo","3214", LocalDate.now()));
        assertFalse(assetEntityRepository
                .existsByTypeOrSerialOrPurchaseDate("NA","1234",LocalDate.of(2021,01,01)));

    }

    @Test
    void testFindBySerial(){
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");
        entityManager.persist(userEntity);
        entityManager.flush();

        AssetEntity assetEntityUser = new AssetEntity();
        assetEntityUser.setUserEntity(userEntity);
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setName("10");
        assetEntityUser.setDescription("10");
        assetEntityUser.setType("Fijo");
        assetEntityUser.setSerial("4321");
        assetEntityUser.setNumInventory("1111");
        assetEntityUser.setWeightKg("10");
        assetEntityUser.setHighCm("10");
        assetEntityUser.setWidthCm("10");
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityUser.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityUser);
        entityManager.flush();

        assertEquals(assetEntityUser, assetEntityRepository
                .findBySerial("4321").get());
    }

    @Test
    void testExistsBySerial(){
        UserEntity userEntity = new UserEntity();
        userEntity.setBirthday(LocalDate.now());
        userEntity.setFirstName("Name 1");
        userEntity.setSecondName("Name 2");
        userEntity.setFirstSurname("Surname 1");
        userEntity.setSecondSurname("Surname 2");
        userEntity.setNumDocument("1234Doc");
        entityManager.persist(userEntity);
        entityManager.flush();

        AssetEntity assetEntityUser = new AssetEntity();
        assetEntityUser.setUserEntity(userEntity);
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setName("10");
        assetEntityUser.setDescription("10");
        assetEntityUser.setType("Fijo");
        assetEntityUser.setSerial("4321");
        assetEntityUser.setNumInventory("1111");
        assetEntityUser.setWeightKg("10");
        assetEntityUser.setHighCm("10");
        assetEntityUser.setWidthCm("10");
        assetEntityUser.setLongAssetCm("10");
        assetEntityUser.setPurchaseValue(Double.valueOf("1111.2"));
        assetEntityUser.setPurchaseDate(LocalDate.now());
        entityManager.persist(assetEntityUser);
        entityManager.flush();

        assertTrue(assetEntityRepository.existsBySerial("4321"));
        assertFalse(assetEntityRepository.existsBySerial("1234"));
    }


}