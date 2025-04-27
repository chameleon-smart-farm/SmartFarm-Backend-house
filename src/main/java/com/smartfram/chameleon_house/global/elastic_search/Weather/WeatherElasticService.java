package com.smartfram.chameleon_house.global.elastic_search.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherElasticService {

    @Autowired
    private WeatherElasticRepository weatherRepository;

    public WeatherDocument get_weather_info() {

        // 현재 시각
        LocalDateTime now = LocalDateTime.now();

        // yynndd, hhmm 형식으로 변환
        String cur_date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String cur_time = now.format(DateTimeFormatter.ofPattern("HH"));

        log.info("현재 일자 : " + cur_date + "현재 시간 : " + cur_time);

        Optional<WeatherDocument> result = weatherRepository.findByWeatherDateAndWeatherTime(cur_date, cur_time);


        return result.get();
    }


    
}
