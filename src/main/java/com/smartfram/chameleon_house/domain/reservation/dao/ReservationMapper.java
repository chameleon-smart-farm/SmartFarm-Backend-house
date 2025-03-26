package com.smartfram.chameleon_house.domain.reservation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfram.chameleon_house.domain.reservation.dto.ReservationDTO;

@Mapper
public interface ReservationMapper {

    // 예약 정보 리스트 조회
    public List<ReservationDTO> read_reservation_list();

    // 예약 정보 단일 조회
    public ReservationDTO read_reservation(int reservation_id);

    // 예약 정보 DB에 저장
    public void create_reservation(ReservationDTO reservationDTO);

    // 예약 정보 업데이트
    public void update_reservation(ReservationDTO reservationDTO);

    // 예약 정보 삭제
    public void delete_reservation(int reservation_id);
    
}
