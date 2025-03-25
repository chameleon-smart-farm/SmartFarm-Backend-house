package com.smartfram.chameleon_house.domain.weather.dto;

import lombok.Data;

@Data
public class WeatherDataDTO {

    // 습도
    private String weather_hum;

    // 하늘 상태
    private String weather_status;

    // 강수 형태
    private String weather_preci;

    // 1시간 온도
    private String weather_tem;

    // 풍속
    private String weather_wind;

    // 데이터 일자
    private String weather_date;

    // 데이터 시간
    private String weather_time;
}
