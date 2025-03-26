package com.smartfram.chameleon_house.domain.weather.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfram.chameleon_house.domain.weather.dao.WeatherMapper;
import com.smartfram.chameleon_house.domain.weather.dto.WeatherDataDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {

    // 공공 데이터 API 키 - 인코딩된 키 사용
    private String service_key = "yKsymQ7R6rquv%2F2oRf4E28R5lIwHQ15tQRm75yfSgjMLLdDLaQt%2BlOAPsyha0FKI1s5pibCNtv3hXIQwj%2FA70A%3D%3D";
    
    // String 형태의 결과 값
    private String str_result;

    @Autowired(required=true)
    private WeatherMapper weatherMapper;


    /**
     *  기상청 API를 통해 단기 예보 데이터를 가져온다.
     *  예보 데이터는 새벽 5시를 기준으로 조회하며 일자를 기준으로 모레까지의 데이터를 출력한다.
     *  공공 데이터 포털에 나와있는 샘플 코드 참조하였다.
     *  String 형태의 결과 값을 JsonNode 형태로 변환한다.
     */
    public void save_weather_info(String cur_date){

        try {

            // 기상청 URL에 파라미터값을 추가해 요청
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + service_key); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(cur_date, "UTF-8")); /*당일*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표(정시단위) */
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
            
            // connection 생성
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // 결과값 받아오기
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            // 종료
            rd.close();
            conn.disconnect();

            // 결과 출력
            str_result = sb.toString();
            log.info("결과 : " + str_result);

            // json parser를 통해 JsonNode로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(str_result);

            // JsonNode 에서 실제 데이터가 담긴 items 항목을 가져온다.
            JsonNode items = rootNode.path("response")
                                        .path("body")
                                        .path("items")
                                        .path("item");

            // JsonNode객체를 WeatherDTO, 데이터베이스에 저장하기 위한 DTO로 변환한다.
            to_item_dto(items);                      
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * JsonNode 안의 데이터를 일자로 1차 분류, 시간으로 2차 분류를 한다.
     * Map<String, TreeMap<String, WeatherDataDTO>>은 일자로 1차 분류, 시간으로 2차 분류한 데이터를 저장하는 Map이다.
     * 
     * 
     * @param items : JsonNode 객체
     */
    public void to_item_dto (JsonNode items){
        // 일자와 시간별로 WeatherDataDTO객체를 저장 (1차 분류 : 일자 / 2차 분류 : 시간)
        Map<String, TreeMap<String, WeatherDataDTO>> weatherDataMap = new TreeMap<>();
    
        // JsonNode 객체를 순회하면서 각각의 일자, 시간, 카테고리, 데이터 값을 가져온다.
        // 일자 : fcstDate / 시간 : fcstTime / 카테고리 : category / 데이터 값 : fcstValue
        for(JsonNode item : items){
            String fcstDate = item.path("fcstDate").asText();
            String fcstTime = item.path("fcstTime").asText();
            String category = item.path("category").asText();
            String fcstValue = item.path("fcstValue").asText();
        
            // key값인 일자(fcstDate)에 해당하는 Map 가져오기 또는 새로 생성
            weatherDataMap.putIfAbsent(fcstDate, new TreeMap<>());
            Map<String, WeatherDataDTO> timeMap = weatherDataMap.get(fcstDate);

            // key값인 시간(fcstTime)에 해당하는 WeatherDTO 객체 가져오기 또는 새로 생성
            timeMap.putIfAbsent(fcstTime, new WeatherDataDTO());
            WeatherDataDTO weatherDTO = timeMap.get(fcstTime);

            // weatherDTO에 날짜와 시간을 저장
            weatherDTO.setWeather_date(fcstDate);
            weatherDTO.setWeather_time(fcstTime);

            // CATEGORY에 따라 fcstValue 저장
            switch (category) {
                // 습도
                case "REH": 
                    weatherDTO.setWeather_hum(fcstValue);
                    break;
                // 하늘 상태
                case "SKY":
                    switch (fcstValue) {
                        case "1":
                            weatherDTO.setWeather_status("맑음");
                            break;
                        case "3":
                            weatherDTO.setWeather_status("구름 많음");
                            break;
                        case "4":
                            weatherDTO.setWeather_status("흐림");
                            break;
                        default:
                            break;
                    }
                    break;
                // 강수 형태
                case "PTY" :
                    switch (fcstValue) {
                        case "0":
                            weatherDTO.setWeather_preci("X");
                            break;
                        case "1":
                            weatherDTO.setWeather_preci("비");
                            break;
                        case "2":
                            weatherDTO.setWeather_preci("비/눈");
                            break;
                        case "3":
                            weatherDTO.setWeather_preci("눈");
                            break;
                        case "4":
                            weatherDTO.setWeather_preci("소나기");
                            break;
                        default:
                            break;
                    }
                // 온도
                case "TMP":
                    weatherDTO.setWeather_tem(fcstValue);
                    break;
                // 풍속
                case "WSD":
                    weatherDTO.setWeather_wind(fcstValue);
                    break;
            }
        
        }
      
        // 결과 출력
        for (Map.Entry<String, TreeMap<String, WeatherDataDTO>> dateEntry : weatherDataMap.entrySet()) {
            String date = dateEntry.getKey();
            for (Map.Entry<String, WeatherDataDTO> timeEntry : dateEntry.getValue().entrySet()) {
                String time = timeEntry.getKey();
                WeatherDataDTO weatherDTO = timeEntry.getValue();
                System.out.println("Date: " + date + ", Time: " + time + ", 습도: " + weatherDTO.getWeather_hum() + ", 하늘 상태: " + weatherDTO.getWeather_status() + ", 강수 형태: " + weatherDTO.getWeather_preci() + ", 온도: " + weatherDTO.getWeather_tem() + ", 풍속: " + weatherDTO.getWeather_wind());
            }
        }

        // 데이터베이스에 값 삽입
        insert_weather_data(weatherDataMap);
    }

    /**
     * Map을 List로 변환해 Mapper를 호출해 데이터베이스에 넣는다.
     * 이때 마지막 데이터는 4일째의 첫번째 00시 데이터만을 담고 있으므로 저장하지 않는다.
     * 4일째부터는 기상청 API를 통해서 다시 데이터를 받아와야 한다.
     * 
     * @param weatherDataMap : 일자로 1차 분류, 시간으로 2차 분류한 데이터를 저장하는 Map이다.
     */
    public void insert_weather_data(Map<String, TreeMap<String, WeatherDataDTO>> weatherDataMap){
        // List 객체
        List<WeatherDataDTO> weatherDataList = new ArrayList<>();

        // List로 변환
        for (Map<String, WeatherDataDTO> timeMap : weatherDataMap.values()) {
            for (WeatherDataDTO weatherDTO : timeMap.values()) {
                weatherDataList.add(weatherDTO);
            }
        }

        for (WeatherDataDTO data : weatherDataList){
            log.info(data.toString());
        }

        // 마지막 데이터 삭제
        weatherDataList.remove(weatherDataList.size() - 1);

        // 데이터베이스에 값 저장
        weatherMapper.insert_weather_data(weatherDataList);
    }
    
}
