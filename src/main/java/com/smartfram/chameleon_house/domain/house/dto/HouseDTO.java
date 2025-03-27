package com.smartfram.chameleon_house.domain.house.dto;

import lombok.Data;

@Data
public class HouseDTO {

    // 농장 아이디
    private int house_id;

    // 키우는 작물
    private String house_crop;

    // 농장 주소
    private String house_add;
    
}
