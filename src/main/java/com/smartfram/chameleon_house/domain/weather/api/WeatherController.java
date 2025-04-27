package com.smartfram.chameleon_house.domain.weather.api;

import org.springframework.web.bind.annotation.RestController;

import com.smartfram.chameleon_house.domain.weather.application.WeatherService;
import com.smartfram.chameleon_house.domain.weather.dto.WeatherDataDTO;
import com.smartfram.chameleon_house.global.elastic_search.Weather.WeatherDocument;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    
    @GetMapping("/get_weather_info")
    public ResponseEntity<WeatherDocument> get_weather_info() {
        // weatherService.save_weather_info();

        return new ResponseEntity<>(weatherService.get_weather_info(), HttpStatus.OK);
    }
    
    
}
