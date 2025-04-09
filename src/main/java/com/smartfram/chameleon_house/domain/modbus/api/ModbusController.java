package com.smartfram.chameleon_house.domain.modbus.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;
import com.smartfram.chameleon_house.domain.modbus.application.ModbusService;
import com.smartfram.chameleon_house.domain.reservation.dto.ReservationDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequestMapping("/modbus")
@RestController
@Tag(name = "모드버스 API", description = "PLC에서 호출 또는 PLC에 요청 API")
public class ModbusController {
    
    @Autowired
    private ModbusService modbusService;

    @PostMapping("/insert_tem")
    @Operation(summary = "온도 데이터 저장", description = "PLC에서 전달한 온도 데이터를 DB에 저장하는 API")
    public void set_tem(@RequestBody TemDTO tem) {
        modbusService.set_tem(tem);
    }

    @PostMapping("/insert_reservation")
    @Operation(summary = "예약 데이터 저장", description = "PLC에서 전달한 예약 데이터를 DB에 저장하는 API")
    public void set_reservation(@RequestBody ReservationDTO reservation) {
        modbusService.set_reservation(reservation);
    }
    
    
}
