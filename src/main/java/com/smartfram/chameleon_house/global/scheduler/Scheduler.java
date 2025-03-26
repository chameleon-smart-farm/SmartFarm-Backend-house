package com.smartfram.chameleon_house.global.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smartfram.chameleon_house.domain.weather.application.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private WeatherService weatherService;

    // 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-6)
    @Scheduled(cron = "0 30 5 */3 * *")
    // @Scheduled(cron = "*/10 * * * * *") - test용
    public void getWeatherData() {

        // 현재 시각
        LocalDateTime now = LocalDateTime.now();

        // yynndd, hhmm 형식으로 변환
        String cur_date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        // String cur_time = now.format(DateTimeFormatter.ofPattern("HHmm"));

        weatherService.save_weather_info(cur_date);

    }
    
}
