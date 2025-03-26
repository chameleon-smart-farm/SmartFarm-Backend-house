package com.smartfram.chameleon_house.domain.reservation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.reservation.application.ReservationService;
import com.smartfram.chameleon_house.domain.reservation.dto.ReservationDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Slf4j
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    // 예약 정보 리스트 조회
    @GetMapping("/list")
    @Operation(summary = "예약 정보 리스트 조회" , description = "예약 목록을 조회하는 API")
    public ResponseEntity<List<ReservationDTO>> read_reservation_list(){
        log.info("Reservation Controller : 예약 정보 리스트 조회");
        return new ResponseEntity<>(reservationService.read_reservation_list(), HttpStatus.OK);
    }
    
    // 예약 정보 단일 조회
    @GetMapping("/{reservation_id}")
    @Operation(summary = "예약 정보 단일 조회" , description = "단일 예약을 조회하는 API")
    public ResponseEntity<ReservationDTO> read_reservation(@PathVariable("reservation_id") Integer reservation_id){
        log.info("Reservation Controller : 예약 정보 단일 조회");
        return new ResponseEntity<>(reservationService.read_reservation(reservation_id), HttpStatus.OK);
    }

    // 예약 정보 DB에 저장
    @PostMapping("/insert")
    @Operation(summary = "예약 정보 입력" , description = "예약 정보를 DB에 저장하는 API")
    public void create_reservation(ReservationDTO reservationDTO){
        log.info("Reservation Controller : 예약 정보 DB에 저장");
        reservationService.create_reservation(reservationDTO);
    }
    
    // 예약 정보 업데이트
    @PutMapping("/update")
    @Operation(summary = "예약 정보 수정" , description = "예약 정보를 수정하는 API")
    public void update_reservation(ReservationDTO reservationDTO){
        log.info("Reservation Controller : 예약 정보 업데이트");
        reservationService.update_reservation(reservationDTO);
    }
    
    // 예약 정보 삭제
    @DeleteMapping("/delete")
    @Operation(summary = "예약 정보 삭제" , description = "예약 정보를 삭제하는 API")
    public void delete_reservation(int reservation_id){
        log.info("Reservation Controller : 예약 정보 삭제");
        reservationService.delete_reservation(reservation_id);
    }
    
    
}
