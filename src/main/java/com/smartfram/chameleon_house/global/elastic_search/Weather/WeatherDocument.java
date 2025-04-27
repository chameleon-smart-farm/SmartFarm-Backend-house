package com.smartfram.chameleon_house.global.elastic_search.Weather;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Data;

@Data
@Document(indexName = "weather")
public class WeatherDocument {

    // 아이디
    @Id
    private String weatherId;

    // 습도
    @Field(name = "weather_hum")
    private String weatherHum;

    // 하늘 상태
    @Field(name = "weather_status")
    private String weatherStatus;

    // 강수 형태
    @Field(name = "weather_preci")
    private String weatherPreci;

    // 1시간 온도
    @Field(name = "weather_tem")
    private String weatherTem;

    // 풍속
    @Field(name = "weather_wind")
    private String weatherWind;

    // 데이터 일자
    @Field(name = "weather_date")
    private String weatherDate;

    // 데이터 시간
    @Field(name = "weather_time")
    private String weatherTime;
    
}
