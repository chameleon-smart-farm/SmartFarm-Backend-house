package com.smartfram.chameleon_house.domain.house_status.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfram.chameleon_house.domain.house_status.dao.HouseStatusMapper;
import com.smartfram.chameleon_house.domain.house_status.dto.TemAvgDTO;
import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;
import com.smartfram.chameleon_house.domain.weather.application.WeatherService;
import com.smartfram.chameleon_house.domain.weather.dto.WeatherDataDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HouseStatusService {

    @Autowired
    private HouseStatusMapper houseStatusMapper;

    @Autowired
    private WeatherService weatherService;

    // 가장 최근에 저장된 온도 데이터와 기상청의 데이터를 함께 반환
    public TemDTO get_tem_data(){

        // 가장 최근에 저장된 온도 데이터
        TemDTO result = houseStatusMapper.get_recent_tem();

        // 기상청의 데이터
        WeatherDataDTO weatherDataDTO = weatherService.get_weather_info();
        result.setWeather_tem(Integer.parseInt(weatherDataDTO.getWeather_tem()));

        // 가장 최근 3시간의 평균 온도 데이터
        List<TemAvgDTO> avg_list = houseStatusMapper.get_tem_avg_list();
        result.setAvg_list(avg_list);

        return result;
    }

    // 해당 시간의 -1값에 해당하는 온도 데이터들을 반환
    public List<TemDTO> get_tem_list(String cur_time){
        return houseStatusMapper.get_tem_list(cur_time);
    }

    // 계산한 평균 온도 데이터값을 DB에 저장
    public void set_tem_avg(TemAvgDTO tem_avg){
        houseStatusMapper.set_tem_avg(tem_avg);
    }

    // PLC 호출 - 온도 데이터를 저장
    public void set_tem(TemDTO tem){
        houseStatusMapper.set_tem(tem);
    }
    
}
