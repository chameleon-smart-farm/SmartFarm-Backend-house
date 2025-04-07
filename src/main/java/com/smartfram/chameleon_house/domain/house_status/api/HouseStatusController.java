package com.smartfram.chameleon_house.domain.house_status.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.house_status.application.HouseStatusService;
import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@Tag(name = "농장 상태 확인 API", description = "농장의 각종 상태 데이터 반환")
@RestController
@RequestMapping("/house_status")
public class HouseStatusController {
    
    @Autowired
    private HouseStatusService houseStatusService;

    @GetMapping("/tem_info")
    @Operation(summary = "농장 온도 데이터 조회", description = "농장의 가장 최근 측정 온도값과 기상청의 데이터, 가장 최근 3시간의 평균 온도 데이터를 함께 반환")
    public ResponseEntity<TemDTO> get_tem_data() {
        return new ResponseEntity<>(houseStatusService.get_tem_data(), HttpStatus.OK);
    }
    
}
