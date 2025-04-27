package com.smartfram.chameleon_house.global.elastic_search.Weather;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherElasticRepository extends ElasticsearchRepository<WeatherDocument,String> {

    // elastic search에 기상청 데이터 저장

    // elastic search의 기상청 데이터 가져오기
    Optional<WeatherDocument> findByWeatherDateAndWeatherTime(String weatherDate, String weatherTime);
}