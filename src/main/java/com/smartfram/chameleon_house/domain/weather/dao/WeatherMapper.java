package com.smartfram.chameleon_house.domain.weather.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smartfram.chameleon_house.domain.weather.dto.WeatherDataDTO;

@Mapper
public interface WeatherMapper {

    // DB에 기상청 데이터 저장
    public void create_weather_data(List<WeatherDataDTO> weatherDataList);

    // DB의 기상청 데이터 가져오기
    public WeatherDataDTO read_weather_data(@Param("cur_date") String cur_date, @Param("cur_time") String cur_time);
    
}
