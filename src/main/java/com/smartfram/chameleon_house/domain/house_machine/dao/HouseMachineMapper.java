package com.smartfram.chameleon_house.domain.house_machine.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMachineMapper {
    
    // PLC주소 조회
    public String get_plc_url();

}
