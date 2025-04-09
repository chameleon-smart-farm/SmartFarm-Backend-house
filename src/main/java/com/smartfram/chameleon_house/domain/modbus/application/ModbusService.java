package com.smartfram.chameleon_house.domain.modbus.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfram.chameleon_house.domain.house_status.application.HouseStatusService;
import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;
import com.smartfram.chameleon_house.domain.reservation.application.ReservationService;
import com.smartfram.chameleon_house.domain.reservation.dto.ReservationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModbusService {

    @Autowired
    private HouseStatusService houseStatusService;

    @Autowired
    private ReservationService reservationService;

    // PLC 호출 - 온도 데이터를 저장
    public void set_tem(TemDTO tem){
        houseStatusService.set_tem(tem);
    }
    
    // PLC 호출 - 예약 데이터를 저장
    public void set_reservation(ReservationDTO reservationDTO){
        reservationService.create_reservation(reservationDTO);
    }
}
