package com.smartfram.chameleon_house.domain.house.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.house.application.HouseService;
import com.smartfram.chameleon_house.domain.house.dto.HouseDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RequestMapping("/house")
@RestController
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/info")
    @Operation(summary = "농장 정보 조회" , description = "키우는 작물, 주소를 조회하는 API")
    public ResponseEntity<HouseDTO> read_house() {
        return new ResponseEntity<>(houseService.read_house(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "농장 정보 수정" , description = "키우는 작물을 수정하는 API")
    public void update_house( @RequestBody HouseDTO houseDTO) {
        houseService.update_house(houseDTO);
    }
    
    
}
