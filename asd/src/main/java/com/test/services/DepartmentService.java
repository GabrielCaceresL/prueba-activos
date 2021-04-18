package com.test.services;

import com.test.api.request.DepartmentDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.CityEntity;
import com.test.entity.DepartmentEntity;
import com.test.mappers.IDepartmentMapper;
import com.test.repository.CityEntityRepository;
import com.test.repository.DepartmentEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class DepartmentService implements IDepartmentService {

    private final DepartmentEntityRepository departmentEntityRepository;
    private final CityEntityRepository cityEntityRepository;

    public DepartmentService(DepartmentEntityRepository departmentEntityRepository, CityEntityRepository cityEntityRepository) {
        this.departmentEntityRepository = departmentEntityRepository;
        this.cityEntityRepository = cityEntityRepository;
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> save(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = new DepartmentEntity();//Creación del objeto
        departmentEntity.setName(departmentDto.getName());//Creación del objeto
        return getDepartmentDtoGeneralResponse(departmentDto, departmentEntity);
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> update(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = departmentEntityRepository.findByName(departmentDto.getName());

        return getDepartmentDtoGeneralResponse(departmentDto, departmentEntity);
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> get(String name) {
        log.info("name city -> {}", name);
        DepartmentEntity departmentEntity = departmentEntityRepository.findByName(name);
        log.info("departmentEntity -> {}", departmentEntity);
        DepartmentDto departmentDto = IDepartmentMapper.INSTANCE.toDto(departmentEntity);
        departmentDto.setCityEntities(departmentEntity.nameCities());
        return Optional.ofNullable(new GeneralResponse<>(departmentDto, ""));
    }

    private Optional<GeneralResponse<DepartmentDto>> getDepartmentDtoGeneralResponse(DepartmentDto departmentDto, DepartmentEntity departmentEntity) {
        Set<CityEntity> cities = cityEntityRepository.findAllByNameIn(departmentDto.getCityEntities()); //Encontrar ciudades
        departmentEntity.setCityEntities(cities); //Actualizar objeto con ciudades
        DepartmentEntity departmentEntity1 = departmentEntityRepository.save(departmentEntity);  //Guardar el objeto

        DepartmentDto departmentDtoResponse = IDepartmentMapper.INSTANCE
                .toDto(departmentEntityRepository.save(departmentEntity)); //Crea el response
        departmentDtoResponse.setCityEntities(departmentEntity1.nameCities()); //ASigna los nombres de ciudades al response
        return Optional.ofNullable(new GeneralResponse<>(departmentDtoResponse, ""));
    }


}
