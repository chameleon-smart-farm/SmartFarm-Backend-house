package com.smartfram.chameleon_house.domain.house.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfram.chameleon_house.domain.house.dao.HouseMapper;
import com.smartfram.chameleon_house.domain.house.dto.HouseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;

    // 농장 정보 조회
    public HouseDTO read_house(){
        return houseMapper.read_house();
    }

    // 농장 정보 수정
    public void update_house(HouseDTO houseDTO){
        houseMapper.update_house(houseDTO);
    }
}
