package com.test.controller;

import com.test.api.request.CityDto;
import com.test.api.request.DepartmentDto;
import com.test.api.response.GeneralResponse;
import com.test.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final IDepartmentService iDepartmentService;

    @PostMapping
    public ResponseEntity<Optional<GeneralResponse<DepartmentDto>>> save(@Valid @RequestBody DepartmentDto body){
        return ResponseEntity.ok(iDepartmentService.save(body));
    }

    @PutMapping
    public ResponseEntity<Optional<GeneralResponse<DepartmentDto>>> update(@RequestBody DepartmentDto body){
        return ResponseEntity.ok(iDepartmentService.update(body));
    }

    @GetMapping
    public ResponseEntity<Optional<GeneralResponse<DepartmentDto>>> get(@RequestParam("departmentName") String departmentName){
        log.info("Controller param department name -> {}", departmentName);
        return ResponseEntity.ok(iDepartmentService.get(departmentName));
    }

}
