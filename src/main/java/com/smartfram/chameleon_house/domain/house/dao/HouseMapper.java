package com.smartfram.chameleon_house.domain.house.dao;

import org.apache.ibatis.annotations.Mapper;

import com.smartfram.chameleon_house.domain.house.dto.HouseDTO;

@Mapper
public interface HouseMapper {

    // 농장 정보 조회
    public HouseDTO read_house();

    // 농장 정보 수정
    public void update_house(HouseDTO houseDTO);
}
