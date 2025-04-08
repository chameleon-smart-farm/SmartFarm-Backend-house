package com.smartfram.chameleon_house.domain.house_machine.application;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfram.chameleon_house.domain.house_machine.dao.HouseMachineMapper;
import com.smartfram.chameleon_house.domain.house_machine.dto.HouseMachineDTO;
import com.smartfram.chameleon_house.global.toPLC.HttpPLC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HouseMachineService {

    @Autowired
    private HttpPLC httpplc;

    @Autowired
    private HouseMachineMapper houseMachineMapper;

    // PLC에 모터 상태를 조회 요청을 보내는 메서드
    public HouseMachineDTO get_motor_status(){

        // PLC 주소 받아오기 - 추후에 uri path추가
        String plc_url = houseMachineMapper.get_plc_url();

        // get 요청
        JSONObject res_result = (JSONObject) httpplc.get_http_connection(plc_url).get();

        // 결과 생성
        HouseMachineDTO result = new HouseMachineDTO();
        result.setMotor_status(Boolean.parseBoolean(res_result.get("status").toString()));

        // 결과 출력
        log.info("get_motor_status - 모터 상태 : " + result.isMotor_status());

        return result;
    }
    
    // PLC에 모터 on/off 요청을 보내는 메서드
    public void motor_on_off(HouseMachineDTO status){

        log.info("모터 on/off 여부 : " + status.isMotor_status());

        // PLC 주소 받아오기 - 추후에 uri 추가
        String plc_url = houseMachineMapper.get_plc_url();

        // post 요청
        httpplc.post_http_connection(plc_url, status.isMotor_status());

    }
}
