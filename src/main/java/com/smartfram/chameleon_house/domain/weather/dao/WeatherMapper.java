package com.smartfram.chameleon_house.domain.weather.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfram.chameleon_house.domain.weather.dto.WeatherDataDTO;

@Mapper
public interface WeatherMapper {

    public void insert_weather_data(List<WeatherDataDTO> weatherDataList);
    
}
