package com.smartfram.chameleon_house.domain.house_status.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smartfram.chameleon_house.domain.house_status.dto.TemAvgDTO;
import com.smartfram.chameleon_house.domain.house_status.dto.TemDTO;

@Mapper
public interface HouseStatusMapper {

    // 가장 최근에 저장된 온도 데이터
    public TemDTO get_recent_tem();

    // 가장 최근 3시간의 평균 온도 데이터
    public List<TemAvgDTO> get_tem_avg_list();

    // 해당 시간의 -1값에 해당하는 온도 데이터들을 반환
    public List<TemDTO> get_tem_list(@Param("cur_time") String cur_time);

    // 계산한 평균 온도 데이터값을 DB에 저장
    public void set_tem_avg(TemAvgDTO tem_avg);
    
}
