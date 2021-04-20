package com.test.controller;

import com.test.api.request.CityDto;
import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;
import com.test.services.ICityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
@Slf4j
public class CityController {

    private final ICityService iCityService;

    @PostMapping
    public ResponseEntity<Optional<GeneralResponse<CityDto>>> save(@Valid @RequestBody CityDto body){
        return ResponseEntity.ok(iCityService.save(body));
    }

    @PutMapping
    public ResponseEntity<Optional<GeneralResponse<CityDto>>> update(@RequestBody CityDto body){
        log.info("body -> {}",body.toString());
        return ResponseEntity.ok(iCityService.update(body));
    }

    @GetMapping
    public ResponseEntity<Optional<GeneralResponse<CityDto>>> get(@PathParam("codeCity") String codeCity){
        return ResponseEntity.ok(iCityService.get(codeCity));
    }


}
