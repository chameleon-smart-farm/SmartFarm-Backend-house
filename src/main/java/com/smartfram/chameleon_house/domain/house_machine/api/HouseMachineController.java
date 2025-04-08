package com.smartfram.chameleon_house.domain.house_machine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.house_machine.application.HouseMachineService;
import com.smartfram.chameleon_house.domain.house_machine.dto.HouseMachineDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@Tag(name = "농장 기기 API", description = "농장의 각종 기기 데이터 상태 조회와 동작 관리")
@RestController
@RequestMapping("/house_machine")
public class HouseMachineController {

    @Autowired
    private HouseMachineService houseMachineService;

    @GetMapping("/motor/status")
    public ResponseEntity<HouseMachineDTO> get_motor_status() {
        return new ResponseEntity<>(houseMachineService.get_motor_status(), HttpStatus.OK);
    }
    

    @PostMapping("/motor/on_off")
    @Operation(summary = "모터 동작 API", description = "status 값에 따라 모터를 ON/OFF 하는 API")
    public void motor_on_off(@RequestBody HouseMachineDTO status) {
        houseMachineService.motor_on_off(status);
    }
    
    
}
