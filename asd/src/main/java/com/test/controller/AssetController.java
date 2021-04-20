package com.test.controller;

import com.test.api.request.AssetAreaDto;
import com.test.api.request.AssetSearchDto;
import com.test.api.request.AssetUserDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.AssetEntity;
import com.test.services.IAssetService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset")
@RequiredArgsConstructor
public class AssetController {

    private final IAssetService iAssetService;

    @PostMapping("/asset-for-area")
    public ResponseEntity<Optional<GeneralResponse<AssetAreaDto>>> saveAssetForArea(@Valid @RequestBody AssetAreaDto body){
        return ResponseEntity.ok(iAssetService.save(body));
    }

    @PostMapping("/asset-for-user")
    public ResponseEntity<Optional<GeneralResponse<AssetUserDto>>> saveAssetForUser(@Valid @RequestBody AssetUserDto body){

        return ResponseEntity.ok(iAssetService.save(body));
    }

    @PutMapping("/asset-for-area")
    public ResponseEntity<Optional<GeneralResponse<AssetAreaDto>>> updateForArea(@Valid @RequestBody AssetAreaDto body){
        return ResponseEntity.ok(iAssetService.update(body));
    }

    @PutMapping("/asset-for-user")
    public ResponseEntity<Optional<GeneralResponse<AssetUserDto>>> updateForArea(@Valid @RequestBody AssetUserDto body){
        return ResponseEntity.ok(iAssetService.update(body));
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<List<AssetEntity>>> getForAsset(@RequestBody AssetSearchDto body){
        return ResponseEntity.ok(iAssetService.get(body));
    }
}
