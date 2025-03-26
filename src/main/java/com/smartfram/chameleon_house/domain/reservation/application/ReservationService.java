package com.smartfram.chameleon_house.domain.reservation.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfram.chameleon_house.domain.reservation.dao.ReservationMapper;
import com.smartfram.chameleon_house.domain.reservation.dto.ReservationDTO;

@Service
public class ReservationService {

    @Autowired
    ReservationMapper reservationMapper;

    // 예약 정보 리스트 조회
    public List<ReservationDTO> read_reservation_list(){
        return reservationMapper.read_reservation_list();
    }

    // 예약 정보 단일 조회
    public ReservationDTO read_reservation(int reservation_id){
        return reservationMapper.read_reservation(reservation_id);
    }

    // 예약 정보 DB에 저장
    public void create_reservation(ReservationDTO reservationDTO){
        reservationMapper.create_reservation(reservationDTO);
    }

    // 예약 정보 업데이트
    public void update_reservation(ReservationDTO reservationDTO){
        reservationMapper.update_reservation(reservationDTO);
    }

    // 예약 정보 삭제
    public void delete_reservation(int reservation_id){
        reservationMapper.delete_reservation(reservation_id);
    }
}
