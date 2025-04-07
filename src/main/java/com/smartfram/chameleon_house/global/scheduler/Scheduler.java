package com.smartfram.chameleon_house.global.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smartfram.chameleon_house.domain.house_status.application.HouseStatusService;
import com.smartfram.chameleon_house.domain.house_status.dto.TemAvgDTO;
import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;
import com.smartfram.chameleon_house.domain.weather.application.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private HouseStatusService houseStatusService;

    // 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-6)
    @Scheduled(cron = "0 30 5 */3 * *")
    // @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void getWeatherData() {

        // 현재 시각
        LocalDateTime now = LocalDateTime.now();

        // yynndd으로 변환
        String cur_date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        weatherService.save_weather_info(cur_date);

    }

    @Scheduled(cron = "0 0 0/1 1/1 * ?")
    public void setTemAvg(){

        // 현재 시각에서 -1값
        LocalDateTime now = LocalDateTime.now().minusHours(1);

        // hh 형식으로 변환
        String cur_time = now.format(DateTimeFormatter.ofPattern("HH"));

        List<TemDTO> tem_list = houseStatusService.get_tem_list(cur_time);

        int tem_sum = 0;

        for(TemDTO t : tem_list){
            tem_sum += t.getTem_data();
        }

        log.info("온도 데이터 총합 : " + tem_sum);

        TemAvgDTO tem_avg = new TemAvgDTO();
        tem_avg.setTem_avg_data(tem_sum/12);
        tem_avg.setTem_avg_fin_time(cur_time);

        log.info("평균 온도 데이터 계산 값 : " + tem_avg.toString());

        houseStatusService.set_tem_avg(tem_avg);
    }
    
}
